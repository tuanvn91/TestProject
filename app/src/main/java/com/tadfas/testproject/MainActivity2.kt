package com.tadfas.testproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.StreamingService
import org.schabi.newpipe.extractor.StreamingService.LinkType
import org.schabi.newpipe.extractor.exceptions.ExtractionException
import org.schabi.newpipe.extractor.search.SearchInfo
import java.util.concurrent.Callable

class MainActivity2 : AppCompatActivity() {
    private var searchDisposable: Disposable? = null
    private val suggestionDisposable: Disposable? = null
    private val disposables = CompositeDisposable()
    private val suggestionPublisher = PublishSubject.create<String?>()
    private var searchString = "khac viet"
    var contentFilter = arrayOfNulls<String>(0)
    var sortFilter: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        val text : TextView = findViewById(R.id.bottomsheet_text)
//
//        val bottomsheet = BottomSheetDialog(this,R.style.Widget_MaterialComponents_BottomSheet_Modal)
//
//        text.setOnClickListener {
//            bottomsheet.show()
//        }
        search("tuan hung", "aadsf", "null")
    }

    private fun search(
        theSearchString: String,
        theContentFilter: String,
        theSortFilter: String
    ) {

        if (theSearchString.isEmpty()) {
            return
        }
        try {
            val streamingService = NewPipe.getServiceByUrl(theSearchString)
            if (streamingService != null) {
//                showLoading()
                disposables.add(
                    Observable
                        .fromCallable(Callable<Intent> {
                            getIntentByLink(
                                this@MainActivity2,
                                streamingService, theSearchString
                            )
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ intent: Intent? ->
//                        getFM().popBackStackImmediate()
//                        startActivity(intent)
                        }, {
                        })
                )
                return
            }
        } catch (ignored: Exception) {
            // Exception occurred, it's not a url
        }
//        lastSearchedString = this.searchString
        this.searchString = theSearchString
//        infoListAdapter.clearStreamItemList()
//        hideSuggestionsPanel()
//        showMetaInfoInTextView(
//            null, searchBinding.searchMetaInfoTextView,
//            searchBinding.searchMetaInfoSeparator, disposables
//        )
//        hideKeyboardSearch()
//        disposables.add(historyRecordManager.onSearched(serviceId, theSearchString)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { ignored -> }
//            ) { throwable ->
//                showSnackBarError(
//                    ErrorInfo(
//                        throwable, UserAction.SEARCHED,
//                        theSearchString, serviceId
//                    )
//                )
//            })
        suggestionPublisher.onNext(theSearchString)
        startLoading(false)
    }


    fun startLoading(forceLoad: Boolean) {
        disposables.clear()
        if (searchDisposable != null) {
            searchDisposable!!.dispose()
        }
        searchDisposable = searchFor(
            1,
            searchString,
            listOf(*contentFilter),
            sortFilter
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnEvent { searchResult, throwable -> }
            ?.subscribe({ result: SearchInfo ->
                handleResult(result)
            }) { exception: Throwable ->

            }
    }

    fun handleResult(result: SearchInfo) {

        val searchSuggestion = result.searchSuggestion
        val isCorrectedSearch = result.isCorrectedSearch

        /*  val exceptions = result.errors
          if (!exceptions.isEmpty()
              && !(exceptions.size == 1
                      && exceptions[0] is NothingFoundException)
          ) {
              showSnackBarError(
                  ErrorInfo(
                      result.errors, UserAction.SEARCHED,
                      searchString, serviceId
                  )
              )
          }
          searchSuggestion = result.searchSuggestion
          isCorrectedSearch = result.isCorrectedSearch

          // List<MetaInfo> cannot be bundled without creating some containers
          metaInfo = arrayOfNulls<MetaInfo>(result.metaInfo.size)
          metaInfo = result.metaInfo.toArray(metaInfo)
          showMetaInfoInTextView(
              result.metaInfo, searchBinding!!.searchMetaInfoTextView,
              searchBinding!!.searchMetaInfoSeparator, disposables
          )
          handleSearchSuggestion()
          lastSearchedString = searchString
          nextPage = result.nextPage
          if (infoListAdapter.getItemsList().isEmpty()) {
              if (!result.relatedItems.isEmpty()) {
                  infoListAdapter.addInfoItemList(result.relatedItems)
              } else {
                  infoListAdapter.clearStreamItemList()
                  showEmptyState()
                  return
              }
          }
          super.handleResult(result)*/
    }

    fun searchFor(
        serviceId: Int, searchString: String?,
        contentFilter: List<String?>?,
        sortFilter: String?
    ): Single<SearchInfo>? {
//        ExtractorHelper.checkServiceId(serviceId)
        return Single.fromCallable {
            SearchInfo.getInfo(
                NewPipe.getService(serviceId),
                NewPipe.getService(serviceId)
                    .searchQHFactory
                    .fromQuery(searchString, contentFilter, sortFilter)
            )
        }
    }

    @Throws(ExtractionException::class)
    fun getIntentByLink(
        context: Context,
        service: StreamingService,
        url: String
    ): Intent? {
        val linkType = service.getLinkTypeByUrl(url)
        if (linkType == LinkType.NONE) {
            throw ExtractionException(
                "Url not known to service. service=" + service
                        + " url=" + url
            )
        }
        return getOpenIntent(context, url, service.serviceId, linkType)
    }

    private fun getOpenIntent(
        context: Context, url: String,
        serviceId: Int, type: LinkType
    ): Intent? {
        val mIntent = Intent(context, MainActivity::class.java)
        mIntent.putExtra("KEY_SERVICE_ID", serviceId)
        mIntent.putExtra("KEY_URL", url)
        mIntent.putExtra("KEY_LINK_TYPE", type)
        return mIntent
    }
}