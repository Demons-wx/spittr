package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.data.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	
	private SpittleRepository spittrRepository;
	private static final String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";
	
	@Autowired
	public SpittleController(SpittleRepository spittrRepository) { // 注入SpittleRepository
		this.spittrRepository = spittrRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
			@RequestParam(value="count", defaultValue="20") int count) {
		return spittrRepository.findSpittles(max, count);
	}
	
	@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
	public String spittle(@PathVariable long spittleId, Model model) {
		model.addAttribute(spittrRepository.findOne(spittleId));
		return "spittle";	
	}
}
