

@Repository
public interface BookRepository extends R2dbcRepository<Book, Integer>{
    Flux<Book> findByTitleContaining(String title);

    Flux<Book> findByPublished(boolean isPublished);
}