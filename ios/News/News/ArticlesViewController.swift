//
//  ArticlesViewController.swift
//  News
//
//  Created by v.g.ivanov on 17/09/2019.
//  Copyright © 2019 vivanovk26. All rights reserved.
//

import UIKit
import common

class ArticlesViewController: UIViewController, UITableViewDataSource, ActionListener {
    
    @IBOutlet weak var articlesTableView: UITableView!
    
    private var items: [Article] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let articlesListInteractor = CommonDependenciesProvider.init().getArticlesListInteractor()
        articlesListInteractor.loadArticles(actionListener: self)
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
    
    func onNextAction(action: Action) {
        guard let actionItems = (action as? ListAction<Article>)?.items as? [Article] else { return }
        items.append(contentsOf: actionItems)
        articlesTableView.reloadData()
    }
}
