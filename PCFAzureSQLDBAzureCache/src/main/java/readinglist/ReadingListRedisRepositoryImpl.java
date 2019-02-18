package readinglist;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public class ReadingListRedisRepositoryImpl implements CrudRepository<Book, Long> {

	private static final String KEY = "ReadingList";
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, Book> hashOps;

	@Autowired
	public ReadingListRedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {

		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOps = redisTemplate.opsForHash();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		hashOps.delete(KEY, id);

	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Book> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Book> findAll() {
		return hashOps.values(KEY);
	}

	@Override
	public Iterable<Book> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findOne(Long id) {
		return (Book) hashOps.get(KEY, id);

	}

	@Override
	public <S extends Book> S save(S book) {
		hashOps.put(KEY, book.getId(), book);
		return book;
	}

	@Override
	public <S extends Book> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
