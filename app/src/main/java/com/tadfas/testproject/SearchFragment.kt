package com.tadfas.testproject


class SearchFragment /*BaseListFragment<SearchInfo, ListExtractor.InfoItemsPage<?>>*/ {
//    private val suggestionPublisher = PublishSubject.create<String?>()
//    private val disposables = CompositeDisposable()
//
//    @State
//    protected var serviceId = 1
//    protected var infoListAdapter: InfoListAdapter? = null
//
//    @State
//    var filterItemCheckedId = -1
//
//    // these three represents the current search query
//    @State
//    var searchString: String? = null
//
//    /**
//     * No content filter should add like contentFilter = all
//     * be aware of this when implementing an extractor.
//     */
//    @State
//    var contentFilter = arrayOfNulls<String>(0)
//
//    @State
//    var sortFilter: String? = null
//
//    // these represents the last search
//    @State
//    var lastSearchedString: String? = null
//
//    @State
//    var searchSuggestion: String? = null
//
//    @State
//    var isCorrectedSearch = false
//
//    @State
//    var metaInfo: Array<MetaInfo?>?
//
//    @State
//    var wasSearchFocused = false
//    private var menuItemToFilterName: MutableMap<Int, String>? = null
//    private var service: StreamingService? = null
//    private var nextPage: Page? = null
//    private val showLocalSuggestions = true
//    private val showRemoteSuggestions = true
//    private var searchDisposable: Disposable? = null
//    private var suggestionDisposable: Disposable? = null
//
//    //    private HistoryRecordManager historyRecordManager;
//    /*//////////////////////////////////////////////////////////////////////////
//    // Views
//    ////////////////////////////////////////////////////////////////////////// */
//    private var suggestionListAdapter: SuggestionListAdapter? = null
//    private var searchBinding: FragmentSearchBinding? = null
//    private var searchToolbarContainer: View? = null
//    private var searchEditText: EditText? = null
//    private var searchClear: View? = null
//
//    /*//////////////////////////////////////////////////////////////////////// */
//    private var suggestionsPanelVisible = false
//    private var textWatcher: TextWatcher? = null
//    private val wasLoading = AtomicBoolean()
//    private val isLoading = AtomicBoolean()
//
//    /**
//     * Set wasLoading to true so when the fragment onResume is called, the initial search is done.
//     */
//    private fun setSearchOnResume() {
//        wasLoading.set(true)
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Fragment's LifeCycle
//    ////////////////////////////////////////////////////////////////////////// */
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
////        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity());
////        showLocalSuggestions = NewPipeSettings.showLocalSearchSuggestions(requireActivity(), prefs);
////        showRemoteSuggestions = NewPipeSettings.showRemoteSearchSuggestions(requireActivity(), prefs);
//        suggestionListAdapter = SuggestionListAdapter(requireActivity())
//        //        historyRecordManager = new HistoryRecordManager(context);
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_search, container, false)
//    }
//
//    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
//        searchBinding = FragmentSearchBinding.bind(rootView)
//        super.onViewCreated(rootView, savedInstanceState)
//        initViews(rootView, savedInstanceState)
//        showSearchOnStart()
//        initSearchListeners()
//    }
//
//    private fun updateService() {
//        try {
//            service = NewPipe.getService(serviceId)
//        } catch (e: Exception) {
////            ErrorUtil.showUiErrorSnackbar(this, "Getting service for id " + serviceId, e);
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        updateService()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        wasSearchFocused = searchEditText.hasFocus()
//        if (searchDisposable != null) {
//            searchDisposable!!.dispose()
//        }
//        if (suggestionDisposable != null) {
//            suggestionDisposable!!.dispose()
//        }
//        disposables.clear()
//        hideKeyboardSearch()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (suggestionDisposable == null || suggestionDisposable!!.isDisposed) {
//            initSuggestionObserver()
//        }
//        if (!TextUtils.isEmpty(searchString)) {
//            if (wasLoading.getAndSet(false)) {
//                search(searchString, contentFilter, sortFilter)
//                return
//            } else if (infoListAdapter.getItemsList().isEmpty()) {
//                if (savedState == null) {
//                    search(searchString, contentFilter, sortFilter)
//                    return
//                } else if (!isLoading.get() && !wasSearchFocused && lastPanelError == null) {
//                    infoListAdapter.clearStreamItemList()
//                    showEmptyState()
//                }
//            }
//        }
//        handleSearchSuggestion()
//        showMetaInfoInTextView(
//            if (metaInfo == null) null else Arrays.asList(*metaInfo),
//            searchBinding!!.searchMetaInfoTextView, searchBinding!!.searchMetaInfoSeparator,
//            disposables
//        )
//        if (TextUtils.isEmpty(searchString) || wasSearchFocused) {
//            showKeyboardSearch()
//            showSuggestionsPanel()
//        } else {
//            hideKeyboardSearch()
//            hideSuggestionsPanel()
//        }
//        wasSearchFocused = false
//    }
//
//    override fun onDestroyView() {
//        unsetSearchListeners()
//        searchBinding = null
//        super.onDestroyView()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (searchDisposable != null) {
//            searchDisposable!!.dispose()
//        }
//        if (suggestionDisposable != null) {
//            suggestionDisposable!!.dispose()
//        }
//        disposables.clear()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        /* if (requestCode == ReCaptchaActivity.RECAPTCHA_REQUEST) {
//            if (resultCode == Activity.RESULT_OK
//                    && !TextUtils.isEmpty(searchString)) {
//                search(searchString, contentFilter, sortFilter);
//            } else {
//                Log.e(TAG, "ReCaptcha failed");
//            }
//        } else {
//            Log.e(TAG, "Request code from activity not supported [" + requestCode + "]");
//        }*/
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Init
//    ////////////////////////////////////////////////////////////////////////// */
//    protected fun initViews(rootView: View?, savedInstanceState: Bundle?) {
////        super.initViews(rootView, savedInstanceState);
//        searchBinding!!.suggestionsList.adapter = suggestionListAdapter
//        ItemTouchHelper(object : ItemTouchHelper.Callback() {
//            override fun getMovementFlags(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder
//            ): Int {
//                return getSuggestionMovementFlags(viewHolder)
//            }
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                viewHolder1: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
//                onSuggestionItemSwiped(viewHolder)
//            }
//        }).attachToRecyclerView(searchBinding!!.suggestionsList)
//        searchToolbarContainer = requireActivity().findViewById(R.id.toolbar_search_container)
//        searchEditText =
//            searchToolbarContainer.findViewById<EditText>(R.id.toolbar_search_edit_text)
//        searchClear = searchToolbarContainer.findViewById(R.id.toolbar_search_clear)
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // State Saving
//    ////////////////////////////////////////////////////////////////////////// */
//    //    @Override
//    //    public void writeTo(final Queue<Object> objectsToSave) {
//    //        super.writeTo(objectsToSave);
//    //        objectsToSave.add(nextPage);
//    //    }
//    //
//    //    @Override
//    //    public void readFrom(@NonNull final Queue<Object> savedObjects) throws Exception {
//    //        super.readFrom(savedObjects);
//    //        nextPage = (Page) savedObjects.poll();
//    //    }
//    override fun onSaveInstanceState(bundle: Bundle) {
//        searchString =
//            if (searchEditText != null) searchEditText.getText().toString() else searchString
//        super.onSaveInstanceState(bundle)
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Init's
//    ////////////////////////////////////////////////////////////////////////// */
//    fun reloadContent() {
//        if (!TextUtils.isEmpty(searchString)
//            || searchEditText != null && !TextUtils.isEmpty(searchEditText.getText())
//        ) {
//            search(
//                if (!TextUtils.isEmpty(searchString)) searchString else searchEditText.getText()
//                    .toString(), contentFilter, ""
//            )
//        } else {
//            if (searchEditText != null) {
//                searchEditText.setText("")
//                showKeyboardSearch()
//            }
//            hideErrorPanel()
//        }
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Menu
//    ////////////////////////////////////////////////////////////////////////// */
//    override fun onCreateOptionsMenu(
//        menu: Menu,
//        inflater: MenuInflater
//    ) {
//        super.onCreateOptionsMenu(menu, inflater)
//        val supportActionBar: ActionBar = requireActivity().getSupportActionBar()
//        if (supportActionBar != null) {
//            supportActionBar.setDisplayShowTitleEnabled(false)
//            supportActionBar.setDisplayHomeAsUpEnabled(true)
//        }
//        menuItemToFilterName = HashMap()
//        var itemId = 0
//        var isFirstItem = true
//        val c = context
//        if (service == null) {
//            Log.w(TAG, "onCreateOptionsMenu() called with null service")
//            updateService()
//        }
//        for (filter in service!!.searchQHFactory.availableContentFilter) {
//            if (filter == YoutubeSearchQueryHandlerFactory.MUSIC_SONGS) {
//                val musicItem = menu.add(
//                    2,
//                    itemId++,
//                    0,
//                    "YouTube Music"
//                )
//                musicItem.isEnabled = false
//            } else if (filter == PeertubeSearchQueryHandlerFactory.SEPIA_VIDEOS) {
//                val sepiaItem = menu.add(
//                    2,
//                    itemId++,
//                    0,
//                    "Sepia Search"
//                )
//                sepiaItem.isEnabled = false
//            }
//            menuItemToFilterName[itemId] = filter
//            val item: MenuItem = menu.add(
//                1,
//                itemId++,
//                0,
//                ServiceHelper.getTranslatedFilterString(filter, c)
//            )
//            if (isFirstItem) {
//                item.isChecked = true
//                isFirstItem = false
//            }
//        }
//        menu.setGroupCheckable(1, true, true)
//        restoreFilterChecked(menu, filterItemCheckedId)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (menuItemToFilterName != null) {
//            val cf: MutableList<String?> = ArrayList(1)
//            cf.add(menuItemToFilterName!![item.itemId])
//            changeContentFilter(item, cf)
//        }
//        return true
//    }
//
//    private fun restoreFilterChecked(menu: Menu, itemId: Int) {
//        if (itemId != -1) {
//            val item = menu.findItem(itemId) ?: return
//            item.isChecked = true
//        }
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Search
//    ////////////////////////////////////////////////////////////////////////// */
//    private fun showSearchOnStart() {
//        if (DEBUG) {
//            Log.d(
//                TAG, "showSearchOnStart() called, searchQuery → "
//                        + searchString
//                        + ", lastSearchedQuery → "
//                        + lastSearchedString
//            )
//        }
//        searchEditText.setText(searchString)
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            searchEditText.setHintTextColor(searchEditText.getTextColors().withAlpha(128))
//        }
//        if (TextUtils.isEmpty(searchString) || TextUtils.isEmpty(searchEditText.getText())) {
//            searchToolbarContainer!!.translationX = 100f
//            searchToolbarContainer!!.alpha = 0.0f
//            searchToolbarContainer!!.visibility = View.VISIBLE
//            searchToolbarContainer!!.animate()
//                .translationX(0f)
//                .alpha(1.0f)
//                .setDuration(200)
//                .setInterpolator(DecelerateInterpolator()).start()
//        } else {
//            searchToolbarContainer!!.translationX = 0f
//            searchToolbarContainer!!.alpha = 1.0f
//            searchToolbarContainer!!.visibility = View.VISIBLE
//        }
//    }
//
//    private fun initSearchListeners() {
//        if (DEBUG) {
//            Log.d(TAG, "initSearchListeners() called")
//        }
//        searchClear!!.setOnClickListener { v: View ->
//            if (DEBUG) {
//                Log.d(TAG, "onClick() called with: v = [$v]")
//            }
//            if (TextUtils.isEmpty(searchEditText.getText())) {
//                NavigationHelper.gotoMainFragment(getFM())
//                return@setOnClickListener
//            }
//            searchBinding!!.correctSuggestion.visibility = View.GONE
//            searchEditText.setText("")
//            suggestionListAdapter!!.setItems(ArrayList())
//            showKeyboardSearch()
//        }
//        TooltipCompat.setTooltipText(searchClear, getString(R.string.clear))
//        searchEditText.setOnClickListener(View.OnClickListener { v: View ->
//            if (DEBUG) {
//                Log.d(TAG, "onClick() called with: v = [$v]")
//            }
//            if ((showLocalSuggestions || showRemoteSuggestions) && !isErrorPanelVisible()) {
//                showSuggestionsPanel()
//            }
//            if (DeviceUtils.isTv(context)) {
//                showKeyboardSearch()
//            }
//        })
//        searchEditText.setOnFocusChangeListener(OnFocusChangeListener { v: View, hasFocus: Boolean ->
//            if (DEBUG) {
//                Log.d(
//                    TAG, "onFocusChange() called with: "
//                            + "v = [" + v + "], hasFocus = [" + hasFocus + "]"
//                )
//            }
//            if ((showLocalSuggestions || showRemoteSuggestions)
//                && hasFocus && !isErrorPanelVisible()
//            ) {
//                showSuggestionsPanel()
//            }
//        })
//        suggestionListAdapter!!.setListener(object : OnSuggestionItemSelected {
//            override fun onSuggestionItemSelected(item: SuggestionItem?) {
//                search(item!!.query, arrayOfNulls(0), "")
//                searchEditText.setText(item.query)
//            }
//
//            override fun onSuggestionItemInserted(item: SuggestionItem?) {
//                searchEditText.setText(item!!.query)
//                searchEditText.setSelection(searchEditText.getText().length)
//            }
//
//            override fun onSuggestionItemLongClick(item: SuggestionItem?) {
//                if (item!!.fromHistory) {
//                    showDeleteSuggestionDialog(item)
//                }
//            }
//        })
//        if (textWatcher != null) {
//            searchEditText.removeTextChangedListener(textWatcher)
//        }
//        textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(
//                s: CharSequence, start: Int,
//                count: Int, after: Int
//            ) {
//            }
//
//            override fun onTextChanged(
//                s: CharSequence, start: Int,
//                before: Int, count: Int
//            ) {
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                // Remove rich text formatting
//                for (span in s.getSpans<CharacterStyle>(0, s.length, CharacterStyle::class.java)) {
//                    s.removeSpan(span)
//                }
//                val newText: String = searchEditText.getText().toString()
//                suggestionPublisher.onNext(newText)
//            }
//        }
//        searchEditText.addTextChangedListener(textWatcher)
//        searchEditText.setOnEditorActionListener(
//            OnEditorActionListener { v: TextView, actionId: Int, event: KeyEvent? ->
//                if (DEBUG) {
//                    Log.d(
//                        TAG, "onEditorAction() called with: v = [" + v + "], "
//                                + "actionId = [" + actionId + "], event = [" + event + "]"
//                    )
//                }
//                if (actionId == EditorInfo.IME_ACTION_PREVIOUS) {
//                    hideKeyboardSearch()
//                } else if (event != null
//                    && (event.keyCode == KeyEvent.KEYCODE_ENTER
//                            || event.action == EditorInfo.IME_ACTION_SEARCH)
//                ) {
//                    search(searchEditText.getText().toString(), arrayOfNulls(0), "")
//                    return@setOnEditorActionListener true
//                }
//                false
//            })
//        if (suggestionDisposable == null || suggestionDisposable!!.isDisposed) {
//            initSuggestionObserver()
//        }
//    }
//
//    private fun unsetSearchListeners() {
//        if (DEBUG) {
//            Log.d(TAG, "unsetSearchListeners() called")
//        }
//        searchClear!!.setOnClickListener(null)
//        searchClear!!.setOnLongClickListener(null)
//        searchEditText.setOnClickListener(null)
//        searchEditText.setOnFocusChangeListener(null)
//        searchEditText.setOnEditorActionListener(null)
//        if (textWatcher != null) {
//            searchEditText.removeTextChangedListener(textWatcher)
//        }
//        textWatcher = null
//    }
//
//    private fun showSuggestionsPanel() {
//        if (DEBUG) {
//            Log.d(TAG, "showSuggestionsPanel() called")
//        }
//        suggestionsPanelVisible = true
//        animate(
//            searchBinding!!.suggestionsPanel, true, 200,
//            AnimationType.LIGHT_SLIDE_AND_ALPHA
//        )
//    }
//
//    private fun hideSuggestionsPanel() {
//        if (DEBUG) {
//            Log.d(TAG, "hideSuggestionsPanel() called")
//        }
//        suggestionsPanelVisible = false
//        animate(
//            searchBinding!!.suggestionsPanel, false, 200,
//            AnimationType.LIGHT_SLIDE_AND_ALPHA
//        )
//    }
//
//    private fun showKeyboardSearch() {
//        if (DEBUG) {
//            Log.d(TAG, "showKeyboardSearch() called")
//        }
//        if (searchEditText == null) {
//            return
//        }
//        if (searchEditText.requestFocus()) {
//            val imm: InputMethodManager = ContextCompat.getSystemService<InputMethodManager>(
//                requireActivity(),
//                InputMethodManager::class.java
//            )
//            imm.showSoftInput(searchEditText, InputMethodManager.SHOW_FORCED)
//        }
//    }
//
//    private fun hideKeyboardSearch() {
//        if (DEBUG) {
//            Log.d(TAG, "hideKeyboardSearch() called")
//        }
//        if (searchEditText == null) {
//            return
//        }
//        val imm: InputMethodManager = ContextCompat.getSystemService<InputMethodManager>(
//            requireActivity(),
//            InputMethodManager::class.java
//        )
//        imm.hideSoftInputFromWindow(
//            searchEditText.getWindowToken(),
//            InputMethodManager.RESULT_UNCHANGED_SHOWN
//        )
//        searchEditText.clearFocus()
//    }
//
//    private fun showDeleteSuggestionDialog(item: SuggestionItem?) {
//        if (requireActivity() == null || historyRecordManager == null || searchEditText == null) {
//            return
//        }
//        val query = item!!.query
//        AlertDialog.Builder(requireActivity())
//            .setTitle(query)
//            .setMessage(R.string.delete_item_search_history)
//            .setCancelable(true)
//            .setNegativeButton(R.string.cancel, null)
//            .setPositiveButton(R.string.delete) { dialog, which ->
//                val onDelete: Disposable = historyRecordManager.deleteSearchHistory(query)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                        { howManyDeleted ->
//                            suggestionPublisher
//                                .onNext(searchEditText.getText().toString())
//                        }
//                    ) { throwable ->
//                        showSnackBarError(
//                            ErrorInfo(
//                                throwable,
//                                UserAction.DELETE_FROM_HISTORY,
//                                "Deleting item failed"
//                            )
//                        )
//                    }
//                disposables.add(onDelete)
//            }
//            .show()
//    }
//
//    override fun onBackPressed(): Boolean {
//        if (suggestionsPanelVisible
//            && !infoListAdapter.getItemsList().isEmpty()
//            && !isLoading.get()
//        ) {
//            hideSuggestionsPanel()
//            hideKeyboardSearch()
//            searchEditText.setText(lastSearchedString)
//            return true
//        }
//        return false
//    }
//
//    private fun getLocalSuggestionsObservable(
//        query: String?, similarQueryLimit: Int
//    ): Observable<MutableList<SuggestionItem>> {
//        return historyRecordManager
//            .getRelatedSearches(query, similarQueryLimit, 25)
//            .toObservable()
//            .map { searchHistoryEntries ->
//                searchHistoryEntries.stream()
//                    .map { entry -> SuggestionItem(true, entry) }
//                    .collect(Collectors.toList())
//            }
//    }
//
//    private fun getRemoteSuggestionsObservable(query: String?): Observable<MutableList<SuggestionItem>> {
//        return ExtractorHelper
//            .suggestionsFor(serviceId, query)
//            .toObservable()
//            .map { strings ->
//                val result: MutableList<SuggestionItem> = ArrayList()
//                for (entry in strings) {
//                    result.add(SuggestionItem(false, entry!!))
//                }
//                result
//            }
//    }
//
//    private fun initSuggestionObserver() {
//        if (DEBUG) {
//            Log.d(TAG, "initSuggestionObserver() called")
//        }
//        if (suggestionDisposable != null) {
//            suggestionDisposable!!.dispose()
//        }
//        suggestionDisposable = suggestionPublisher
//            .debounce(SUGGESTIONS_DEBOUNCE.toLong(), TimeUnit.MILLISECONDS)
//            .startWithItem(if (searchString == null) "" else searchString)
//            .switchMap<Notification<List<SuggestionItem>>> { query: String? ->
//                // Only show remote suggestions if they are enabled in settings and
//                // the query length is at least THRESHOLD_NETWORK_SUGGESTION
//                val shallShowRemoteSuggestionsNow = (showRemoteSuggestions
//                        && query!!.length >= THRESHOLD_NETWORK_SUGGESTION)
//                if (showLocalSuggestions && shallShowRemoteSuggestionsNow) {
//                    return@switchMap Observable.zip<MutableList<SuggestionItem>, MutableList<SuggestionItem>, List<SuggestionItem>>(
//                        getLocalSuggestionsObservable(query, 3),
//                        getRemoteSuggestionsObservable(query),
//                        { local: MutableList<SuggestionItem>, remote: MutableList<SuggestionItem> ->
//                            remote.removeIf { remoteItem: SuggestionItem? ->
//                                local.stream().anyMatch { localItem: SuggestionItem ->
//                                    localItem.equals(remoteItem)
//                                }
//                            }
//                            local.addAll(remote)
//                            local
//                        })
//                        .materialize()
//                } else if (showLocalSuggestions) {
//                    return@switchMap getLocalSuggestionsObservable(query, 25)
//                        .materialize()
//                } else if (shallShowRemoteSuggestionsNow) {
//                    return@switchMap getRemoteSuggestionsObservable(query)
//                        .materialize()
//                } else {
//                    return@switchMap Single.fromCallable<List<SuggestionItem>> { emptyList() }
//                        .toObservable()
//                        .materialize()
//                }
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { listNotification: Notification<List<SuggestionItem>> ->
//                    if (listNotification.isOnNext) {
//                        if (listNotification.value != null) {
//                            handleSuggestions(listNotification.value)
//                        }
//                    } else if (listNotification.isOnError
//                        && listNotification.error != null && !ExceptionUtils.isInterruptedCaused(
//                            listNotification.error
//                        )
//                    ) {
//                        showSnackBarError(
//                            ErrorInfo(
//                                listNotification.error,
//                                UserAction.GET_SUGGESTIONS, searchString, serviceId
//                            )
//                        )
//                    }
//                }) { throwable: Throwable? ->
//                showSnackBarError(
//                    ErrorInfo(
//                        throwable, UserAction.GET_SUGGESTIONS, searchString, serviceId
//                    )
//                )
//            }
//    }
//
//    private fun search(
//        theSearchString: String?,
//        theContentFilter: Array<String?>,
//        theSortFilter: String?
//    ) {
//        if (theSearchString!!.isEmpty()) {
//            return
//        }
//        try {
//            val streamingService: StreamingService = NewPipe.getServiceByUrl(theSearchString)
//            if (streamingService != null) {
////                showLoading();
//                disposables.add(
//                    Observable
//                        .fromCallable<Intent>(Callable<Intent> {
//                            getIntentByLink(
//                                requireActivity(),
//                                streamingService, theSearchString
//                            )
//                        })
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({ intent: Intent? ->
//                            requireActivity().supportFragmentManager.popBackStackImmediate()
//                            requireActivity().startActivity(intent)
//                        }, { throwable: Throwable? -> })
//                )
//                return
//            }
//        } catch (ignored: Exception) {
//            // Exception occurred, it's not a url
//        }
//        lastSearchedString = searchString
//        searchString = theSearchString
//        infoListAdapter.clearStreamItemList()
//        hideSuggestionsPanel()
//        showMetaInfoInTextView(
//            null, searchBinding!!.searchMetaInfoTextView,
//            searchBinding!!.searchMetaInfoSeparator, disposables
//        )
//        hideKeyboardSearch()
//        disposables.add(historyRecordManager.onSearched(serviceId, theSearchString)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { ignored -> }
//            ) { throwable -> })
//        suggestionPublisher.onNext(theSearchString)
//        startLoading(false)
//    }
//
//    fun startLoading(forceLoad: Boolean) {
//        disposables.clear()
//        if (searchDisposable != null) {
//            searchDisposable!!.dispose()
//        }
//        searchDisposable = searchFor(
//            serviceId,
//            searchString,
//            listOf(*contentFilter),
//            sortFilter
//        )
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnEvent { searchResult, throwable -> isLoading.set(false) }
//            ?.subscribe({ result: SearchInfo -> handleResult(result) }) { exception: Throwable ->
//                onItemError(
//                    exception
//                )
//            }
//    }
//
//    fun searchFor(
//        serviceId: Int, searchString: String?,
//        contentFilter: List<String?>?,
//        sortFilter: String?
//    ): Single<SearchInfo>? {
////        ExtractorHelper.checkServiceId(serviceId)
//        return Single.fromCallable {
//            SearchInfo.getInfo(
//                NewPipe.getService(serviceId),
//                NewPipe.getService(serviceId)
//                    .searchQHFactory
//                    .fromQuery(searchString, contentFilter, sortFilter)
//            )
//        }
//    }
//
//    protected fun loadMoreItems() {
//        if (!Page.isValid(nextPage)) {
//            return
//        }
//        isLoading.set(true)
//        showListFooter(true)
//        if (searchDisposable != null) {
//            searchDisposable!!.dispose()
//        }
//        searchDisposable = ExtractorHelper.getMoreSearchItems(
//            serviceId,
//            searchString,
//            Arrays.asList(*contentFilter),
//            sortFilter,
//            nextPage
//        )
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnEvent { nextItemsResult, throwable -> isLoading.set(false) }
//            .subscribe({ result: InfoItemsPage<*> -> handleNextItems(result) }) { exception: Throwable ->
//                onItemError(
//                    exception
//                )
//            }
//    }
//
//    protected fun hasMoreItems(): Boolean {
//        return Page.isValid(nextPage)
//    }
//
//    protected fun onItemSelected(selectedItem: InfoItem?) {
//        super.onItemSelected(selectedItem)
//        hideKeyboardSearch()
//    }
//
//    private fun onItemError(exception: Throwable) {
//        if (exception is NothingFoundException) {
//            infoListAdapter.clearStreamItemList()
//            showEmptyState()
//        } else {
//            showError(ErrorInfo(exception, UserAction.SEARCHED, searchString, serviceId))
//        }
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Utils
//    ////////////////////////////////////////////////////////////////////////// */
//    private fun changeContentFilter(item: MenuItem, theContentFilter: List<String?>) {
//        filterItemCheckedId = item.itemId
//        item.isChecked = true
//        contentFilter = arrayOf(theContentFilter[0])
//        if (!TextUtils.isEmpty(searchString)) {
//            search(searchString, contentFilter, sortFilter)
//        }
//    }
//
//    private fun setQuery(
//        theServiceId: Int,
//        theSearchString: String,
//        theContentFilter: Array<String?>,
//        theSortFilter: String
//    ) {
//        serviceId = theServiceId
//        searchString = theSearchString
//        contentFilter = theContentFilter
//        sortFilter = theSortFilter
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Suggestion Results
//    ////////////////////////////////////////////////////////////////////////// */
//    fun handleSuggestions(suggestions: List<SuggestionItem>) {
//        if (DEBUG) {
//            Log.d(TAG, "handleSuggestions() called with: suggestions = [$suggestions]")
//        }
//        searchBinding!!.suggestionsList.smoothScrollToPosition(0)
//        searchBinding!!.suggestionsList.post { suggestionListAdapter!!.setItems(suggestions) }
//        if (suggestionsPanelVisible && isErrorPanelVisible()) {
//            hideLoading()
//        }
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Contract
//    ////////////////////////////////////////////////////////////////////////// */
//    fun hideLoading() {
//        super.hideLoading()
//        showListFooter(false)
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Search Results
//    ////////////////////////////////////////////////////////////////////////// */
//    fun handleResult(result: SearchInfo) {
//        val exceptions = result.errors
//        if (!exceptions.isEmpty()
//            && !(exceptions.size == 1
//                    && exceptions[0] is NothingFoundException)
//        ) {
//            showSnackBarError(
//                ErrorInfo(
//                    result.errors, UserAction.SEARCHED,
//                    searchString, serviceId
//                )
//            )
//        }
//        searchSuggestion = result.searchSuggestion
//        isCorrectedSearch = result.isCorrectedSearch
//
//        // List<MetaInfo> cannot be bundled without creating some containers
//        metaInfo = arrayOfNulls<MetaInfo>(result.metaInfo.size)
//        metaInfo = result.metaInfo.toArray(metaInfo)
//        showMetaInfoInTextView(
//            result.metaInfo, searchBinding!!.searchMetaInfoTextView,
//            searchBinding!!.searchMetaInfoSeparator, disposables
//        )
//        handleSearchSuggestion()
//        lastSearchedString = searchString
//        nextPage = result.nextPage
//        if (infoListAdapter.getItemsList().isEmpty()) {
//            if (!result.relatedItems.isEmpty()) {
//                infoListAdapter.addInfoItemList(result.relatedItems)
//            } else {
//                infoListAdapter.clearStreamItemList()
//                showEmptyState()
//                return
//            }
//        }
//        super.handleResult(result)
//    }
//
//    private fun handleSearchSuggestion() {
//        if (TextUtils.isEmpty(searchSuggestion)) {
//            searchBinding!!.correctSuggestion.visibility = View.GONE
//        } else {
//            val helperText =
//                getString(if (isCorrectedSearch) R.string.search_showing_result_for else R.string.did_you_mean)
//            val highlightedSearchSuggestion =
//                "<b><i>" + Html.escapeHtml(searchSuggestion) + "</i></b>"
//            val text = String.format(helperText, highlightedSearchSuggestion)
//            searchBinding!!.correctSuggestion.setText(
//                HtmlCompat.fromHtml(
//                    text,
//                    HtmlCompat.FROM_HTML_MODE_LEGACY
//                )
//            )
//            searchBinding!!.correctSuggestion.setOnClickListener { v: View? ->
//                searchBinding!!.correctSuggestion.visibility = View.GONE
//                search(searchSuggestion, contentFilter, sortFilter)
//                searchEditText.setText(searchSuggestion)
//            }
//            searchBinding!!.correctSuggestion.setOnLongClickListener(OnLongClickListener { v: View? ->
//                searchEditText.setText(searchSuggestion)
//                searchEditText.setSelection(searchSuggestion!!.length)
//                showKeyboardSearch()
//                true
//            })
//            searchBinding!!.correctSuggestion.visibility = View.VISIBLE
//        }
//    }
//
//    fun handleNextItems(result: InfoItemsPage<*>) {
//        showListFooter(false)
//        infoListAdapter.addInfoItemList(result.getItems())
//        nextPage = result.getNextPage()
//        if (!result.getErrors().isEmpty()) {
//            showSnackBarError(
//                ErrorInfo(
//                    result.getErrors(), UserAction.SEARCHED,
//                    "\"" + searchString + "\" → pageUrl: " + nextPage!!.url + ", "
//                            + "pageIds: " + nextPage!!.ids + ", "
//                            + "pageCookies: " + nextPage!!.cookies,
//                    serviceId
//                )
//            )
//        }
//        super.handleNextItems(result)
//    }
//
//    fun handleError() {
//        super.handleError()
//        hideSuggestionsPanel()
//        hideKeyboardSearch()
//    }
//
//    /*//////////////////////////////////////////////////////////////////////////
//    // Suggestion item touch helper
//    ////////////////////////////////////////////////////////////////////////// */
//    fun getSuggestionMovementFlags(viewHolder: RecyclerView.ViewHolder): Int {
//        val position: Int = viewHolder.getBindingAdapterPosition()
//        if (position == RecyclerView.NO_POSITION) {
//            return 0
//        }
//        val item = suggestionListAdapter!!.getItem(position)
//        return if (item.fromHistory) ItemTouchHelper.Callback.makeMovementFlags(
//            0,
//            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//        ) else 0
//    }
//
//    fun onSuggestionItemSwiped(viewHolder: RecyclerView.ViewHolder) {
//        val position: Int = viewHolder.getBindingAdapterPosition()
//        val query = suggestionListAdapter!!.getItem(position).query
//        val onDelete: Disposable = historyRecordManager.deleteSearchHistory(query)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { howManyDeleted ->
//                    suggestionPublisher
//                        .onNext(searchEditText.getText().toString())
//                }
//            ) { throwable ->
//                showSnackBarError(
//                    ErrorInfo(
//                        throwable,
//                        UserAction.DELETE_FROM_HISTORY, "Deleting item failed"
//                    )
//                )
//            }
//        disposables.add(onDelete)
//    }
//
//    companion object {
//        /*//////////////////////////////////////////////////////////////////////////
//    // Search
//    ////////////////////////////////////////////////////////////////////////// */
//        /**
//         * The suggestions will only be fetched from network if the query meet this threshold (>=).
//         * (local ones will be fetched regardless of the length)
//         */
//        private const val THRESHOLD_NETWORK_SUGGESTION = 1
//
//        /**
//         * How much time have to pass without emitting a item (i.e. the user stop typing)
//         * to fetch/show the suggestions, in milliseconds.
//         */
//        private const val SUGGESTIONS_DEBOUNCE = 120 //ms
//        fun getInstance(serviceId: Int, searchString: String): SearchFragment {
//            val searchFragment = SearchFragment()
//            searchFragment.setQuery(serviceId, searchString, arrayOfNulls(0), "")
//            if (!TextUtils.isEmpty(searchString)) {
//                searchFragment.setSearchOnResume()
//            }
//            return searchFragment
//        }
//
//        @Throws(ExtractionException::class)
//        fun getIntentByLink(
//            context: Context,
//            service: StreamingService,
//            url: String?
//        ): Intent {
//            val linkType: LinkType = service.getLinkTypeByUrl(url)
//            if (linkType == StreamingService.LinkType.NONE) {
//                throw ExtractionException(
//                    "Url not known to service. service=" + service
//                            + " url=" + url
//                )
//            }
//            return getOpenIntent(context, url, service.serviceId, linkType)
//        }
//
//        private fun getOpenIntent(
//            context: Context, url: String?,
//            serviceId: Int, type: LinkType
//        ): Intent {
//            val mIntent = Intent(context, MainActivity::class.java)
//            mIntent.putExtra("KEY_SERVICE_ID", serviceId)
//            mIntent.putExtra("KEY_URL", url)
//            mIntent.putExtra("KEY_LINK_TYPE", type)
//            return mIntent
//        }
//    }
}