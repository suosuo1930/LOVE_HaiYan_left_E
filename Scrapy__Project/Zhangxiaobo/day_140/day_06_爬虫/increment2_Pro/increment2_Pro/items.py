# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class Increment2ProItem(scrapy.Item):
    # define the fields for your item here like:
    content = scrapy.Field()
    author = scrapy.Field()
