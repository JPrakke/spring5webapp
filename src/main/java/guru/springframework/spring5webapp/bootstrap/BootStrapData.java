package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");
        Publisher twd = new Publisher("TWD Publishing", "121412","Groveland", "FL", "34736");

        publisherRepository.save(twd);

        System.out.printf("Number of Publishers: %d.\n", publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Title Goes Here","46413");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(twd);
        twd.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(twd);


        Author rod = new Author("Ros", "Thompson");
        Book noEJB = new Book("J2EE Development Without EJB", "45632465s");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        ddd.setPublisher(twd);
        twd.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(twd);

        System.out.printf("Number of Books %d.\n", bookRepository.count());
        System.out.printf("Publisher Number of Books: %d.\n", twd.getBooks().size());

    }
}
