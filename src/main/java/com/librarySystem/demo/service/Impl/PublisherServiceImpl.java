package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.Publisher;
import com.librarySystem.demo.repository.PublisherRepository;
import com.librarySystem.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public Publisher getPublisherById(String id) {
        return publisherRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Publisher getPublisherByName(String name) {
        return publisherRepository.findByPublisherName(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public Iterable<Publisher> getPublisher() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(String id, Publisher publisher) {
        if (!publisherRepository.existsById(id)){
            throw new RuntimeException("not found");
        }
        publisher.setId(id);
        return publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(String id) {
        if (!publisherRepository.existsById(id)){
            throw new RuntimeException("not found");
        }
        publisherRepository.deleteById(id);
    }
}
