package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(final AuthorRepository authorRepository, final BookRepository bookRepository, final PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    private void initData(){
        Author eric = new Author("Eric", "Evans");
        Publisher pubEric = new Publisher("Harper Collins", "Fucken nowhere");
        Book ddd = new Book ("Domain Driven Design", "1234", pubEric);
        eric.getBooks().add(ddd);

        authorRepository.save(eric);
        publisherRepository.save(pubEric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Publisher pubRod = new Publisher("Wrox", "Very far away from here");
        Book noEJB = new Book("J2EE Development without EJB", "23444", pubRod);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(pubRod);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
