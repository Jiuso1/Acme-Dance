
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

import domain.Estilo;
import services.CursoService;
import services.EstiloService;

@Controller
@RequestMapping("/estilo")
public class EstiloController {

	@Autowired
	private EstiloService	estiloService;
	@Autowired
	private CursoService	cursoService;

	private int				styleId	= -1;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Estilo> estilos;
		estilos = this.estiloService.findAll();
		result = new ModelAndView("estilo/list");
		result.addObject("requestURI", "estilo/list.do");
		result.addObject("estilos", estilos);
		return result;
	}

	@RequestMapping(value = "/deleteEstilo", method = RequestMethod.GET)
	public ModelAndView deleteEstilo(@RequestParam final int estiloId) {
		ModelAndView result;

		final List<Estilo> estilos = (List<Estilo>) this.estiloService.findById(estiloId);

		final Estilo estilo = estilos.get(0);
		final int numCursosConEstilo = this.cursoService.countCoursesByStyle(estilo);
		if (numCursosConEstilo == 0)
			this.estiloService.delete(estiloId);

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@ModelAttribute("estilo") final Estilo e, final BindingResult bindingResult) {
		ModelAndView result;

		result = new ModelAndView("estilo/create");
		result.addObject("requestURI", "estilo/create.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("estilo") final Estilo e, final BindingResult bindingResult) {
		ModelAndView result;

		final Estilo estilo = new Estilo();

		estilo.setNombre(e.getNombre());
		estilo.setDescripcion(e.getDescripcion());

		try {

			this.estiloService.save(estilo);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:create.do");
			result.addObject("estilo", estilo);
			result.addObject("message", "estilo.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/editEstilo", method = RequestMethod.GET)
	public ModelAndView createEdit(@RequestParam final int styleId) {
		ModelAndView result;

		final List<Estilo> estilos = (List<Estilo>) this.estiloService.findById(styleId);
		final Estilo estilo = estilos.get(0);

		this.styleId = styleId;

		result = new ModelAndView("estilo/edit");
		result.addObject("requestURI", "estilo/editEstilo.do");
		result.addObject("estilo", estilo);

		return result;
	}

	@RequestMapping(value = "/editEstilo", method = RequestMethod.POST)
	public ModelAndView saveEdit(@ModelAttribute("estilo") final Estilo e, final BindingResult bindingResult) {
		ModelAndView result;

		final List<Estilo> estilos = (List<Estilo>) this.estiloService.findById(this.styleId);
		final Estilo estilo = estilos.get(0);

		estilo.setNombre(e.getNombre());
		estilo.setDescripcion(e.getDescripcion());

		//estilo.setCursos();

		this.styleId = -1;

		try {

			this.estiloService.save(estilo);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("estilo", estilo);
			result.addObject("message", "tutorial.commit.error");
		}
		return result;
	}

}
