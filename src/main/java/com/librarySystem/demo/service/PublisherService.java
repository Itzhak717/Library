package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Author;
import com.librarySystem.demo.model.Publisher;

public interface PublisherService {
    Publisher getPublisher(long id);

    Publisher getPublisher(String name);

    Iterable<Publisher> getPublisher();

    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(long id, Publisher publisher);

    void deletePublisher(long id);
}
