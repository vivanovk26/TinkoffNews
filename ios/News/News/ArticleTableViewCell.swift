//
//  ArticleTableViewCell.swift
//  News
//
//  Created by v.g.ivanov on 17/09/2019.
//  Copyright © 2019 vivanovk26. All rights reserved.
//

import UIKit
import common

class ArticleTableViewCell: UITableViewCell {
    
    @IBOutlet weak var articleImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    func configure(article: Article) {
        titleLabel.text = article.name
        descriptionLabel.text = article.text
    }
}
