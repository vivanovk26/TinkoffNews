//
//  BookmarksViewController.swift
//  News
//
//  Created by v.g.ivanov on 17/09/2019.
//  Copyright © 2019 vivanovk26. All rights reserved.
//

import UIKit
import common

class BookmarksViewController: UIViewController, UITableViewDataSource, InteractorListener {
    
    @IBOutlet weak var bookmarksTableView: UITableView!
    
    private var items: [Article] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let articlesListInteractor = CommonDependenciesProvider.init().getInteractorsResolver().provideArticlesListInteractor()
        articlesListInteractor.getArticles(interactorListener: self)
        bookmarksTableView.dataSource = self
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "BookmarkTableViewCell", for: indexPath) as! BookmarkTableViewCell
        cell.configure(article: items[indexPath.row])
        return cell
    }
    
    func onStart() {
        
    }
    
    func onSuccess(result: Array<Article>) {
        items.append(contentsOf: result)
        bookmarksTableView.reloadData()
    }
    
    func onError(throwable: KotlinThrowable) {
        
    }
    
    func onCompletion() {
    
    }
}
