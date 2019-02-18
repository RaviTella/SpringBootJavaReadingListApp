package readinglist;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReadingListRepository extends MongoRepository<Book, String> {

	public List<Book> findByReader(String reader);
	

}
