package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Publisher;

public interface PublisherService {
    Publisher getPublisherById(String id);

    Publisher getPublisherByName(String name);

    Iterable<Publisher> getPublisher();

    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(String id, Publisher publisher);

    void deletePublisher(String id);
}
