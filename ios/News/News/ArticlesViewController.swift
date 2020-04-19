//
//  ArticlesViewController.swift
//  News
//
//  Created by v.g.ivanov on 17/09/2019.
//  Copyright © 2019 vivanovk26. All rights reserved.
//

import UIKit
import common

class ArticlesViewController: UIViewController, UITableViewDataSource, InteractorListener {
    
    @IBOutlet weak var articlesTableView: UITableView!
    
    private var items: [Article] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let articlesListInteractor = CommonDependenciesProvider.init().getInteractorsResolver().provideArticlesListInteractor()
        articlesListInteractor.getArticles(interactorListener: self)
        articlesTableView.dataSource = self
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ArticleTableViewCell", for: indexPath) as! ArticleTableViewCell
        cell.configure(article: items[indexPath.row])
        return cell
    }
    
    func onStart() {
        print("OnStart")
    }
    
    func onSuccess(result: Array<Article>) {
        items.append(contentsOf: result)
        articlesTableView.reloadData()
        print("OnSuccess")
    }
    
    func onError(throwable: KotlinThrowable) {
        print("OnError")
    }
    
    func onCompletion() {
        print("OnCompletion")
    }
}
