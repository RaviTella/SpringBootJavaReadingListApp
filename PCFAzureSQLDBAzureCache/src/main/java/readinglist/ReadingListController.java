package readinglist;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ReadingListController {

	private static final String reader = "craig";

	private ReadingListRepository readingListRepository;
	private ReadingListRedisRepositoryImpl readingListRedisRepository; 

	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository,ReadingListRedisRepositoryImpl readingListRedisRepository) {
		this.readingListRepository = readingListRepository;
		this.readingListRedisRepository = readingListRedisRepository;
	}

	@RequestMapping(value = "/readingList", method = RequestMethod.GET)
	public String readersBooks(Model model) {

		Iterable<Book> readingList = readingListRedisRepository.findAll();
		if (readingList != null) {
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addToReadingList(Book book) {
		book.setReader(reader);
		// TODO add transaction 
		book=readingListRepository.save(book);
		readingListRedisRepository.save(book);
				
		System.out.println(book);
		return "redirect:/readingList";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteFromReadingList(@PathVariable String id) {
		// TODO add transaction 
		readingListRepository.delete(Long.parseLong(id));
		readingListRedisRepository.delete(Long.parseLong(id));
		return "redirect:/readingList";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editReadingListView(@PathVariable String id) {
		ModelAndView model = new ModelAndView("editReadingList");
		Book book = readingListRedisRepository.findOne(Long.parseLong(id));
		
			model.addObject("book", book);
	
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editReadingListItem(Book updatedBook, @RequestParam String action) {

		if (action.equals("update")) {
		Book book = readingListRepository.findOne(updatedBook.getId());
		book.setTitle(updatedBook.getTitle());
		book.setAuthor(updatedBook.getAuthor());
		book.setIsbn(updatedBook.getIsbn());
		book.setDescription(updatedBook.getDescription());	
		// TODO add transaction 
			readingListRepository.save(book);
			readingListRedisRepository.save(book);
			System.out.println(book);

		}

		return "redirect:/readingList";
	}

}

