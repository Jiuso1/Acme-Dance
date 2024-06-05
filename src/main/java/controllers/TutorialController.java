
package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Academia;
import domain.Tutorial;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.AcademiaService;
import services.TutorialService;

@Controller
@RequestMapping("/tutorial")
public class TutorialController {

	@Autowired
	private TutorialService	tutorialService;

	@Autowired
	private AcademiaService	academiaService;

	private int				tutorialId	= -1;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Tutorial> tutoriales;

		final UserAccount user = LoginService.getPrincipal();
		final List<Authority> authorities = (List<Authority>) user.getAuthorities();
		boolean b = false;
		int i = 0;
		while (b == false && i < authorities.size())
			if (authorities.get(i).getAuthority().equals("ACADEMIA"))
				b = true;
			else
				i++;
		if (b == true) {
			final List<Academia> academias = (List<Academia>) this.academiaService.findByUsername(user.getUsername());
			final Academia academia = academias.get(0);
			tutoriales = this.tutorialService.findByAcademia(academia);
			result = new ModelAndView("tutorial/list");
			result.addObject("requestURI", "tutorial/list.do");
			result.addObject("tutoriales", tutoriales);
		} else {
			tutoriales = this.tutorialService.findAll();
			result = new ModelAndView("tutorial/list");
			result.addObject("requestURI", "tutorial/list.do");
			result.addObject("tutoriales", tutoriales);
		}

		return result;
	}

	@RequestMapping(value = "/deleteTutorial", method = RequestMethod.GET)
	public ModelAndView deleteTutorial(@RequestParam final int tutorialId) {
		ModelAndView result;

		this.tutorialService.delete(tutorialId);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@ModelAttribute("tutorial") final Tutorial t, final BindingResult bindingResult) {
		ModelAndView result;

		result = new ModelAndView("tutorial/create");
		result.addObject("requestURI", "tutorial/create.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("tutorial") final Tutorial t, final BindingResult bindingResult) {
		ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final List<Academia> academias = (List<Academia>) this.academiaService.findByUsername(user.getUsername());
		final Academia a = academias.get(0);
		final Tutorial tutorial = new Tutorial();
		tutorial.setTitulo(t.getTitulo());
		tutorial.setDescripcion(t.getDescripcion());
		tutorial.setVideo(t.getVideo());
		tutorial.setAcademia(a);

		try {

			this.tutorialService.save(tutorial);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:create.do");
			result.addObject("tutorial", tutorial);
			result.addObject("message", "tutorial.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/editTutorial", method = RequestMethod.GET)
	public ModelAndView createEdit(@RequestParam final int tutorialId) {
		ModelAndView result;

		final List<Tutorial> tutoriales = (List<Tutorial>) this.tutorialService.findById(tutorialId);
		final Tutorial tutorial = tutoriales.get(0);

		this.tutorialId = tutorialId;

		result = new ModelAndView("tutorial/edit");
		result.addObject("requestURI", "tutorial/editTutorial.do");
		result.addObject("tutorial", tutorial);

		return result;
	}

	@RequestMapping(value = "/editTutorial", method = RequestMethod.POST)
	public ModelAndView saveEdit(@ModelAttribute("tutorial") final Tutorial t, final BindingResult bindingResult) {
		ModelAndView result;

		final List<Tutorial> tutoriales = (List<Tutorial>) this.tutorialService.findById(this.tutorialId);
		final Tutorial tutorial = tutoriales.get(0);

		tutorial.setTitulo(t.getTitulo());
		tutorial.setDescripcion(t.getDescripcion());
		tutorial.setVideo(t.getVideo());

		final Academia academia = tutoriales.get(0).getAcademia();
		tutorial.setAcademia(academia);

		this.tutorialId = -1;

		try {

			this.tutorialService.save(tutorial);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("tutorial", tutorial);
			result.addObject("message", "tutorial.commit.error");
		}
		return result;
	}

}
