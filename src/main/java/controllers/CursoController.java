
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Curso;
import domain.Nivel;
import services.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController extends AbstractController {

	@Autowired
	private CursoService cursoService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curso> cursos;
		cursos = this.cursoService.findAll();
		result = new ModelAndView("curso/list");
		result.addObject("requestURI", "curso/list.do");
		result.addObject("cursos", cursos);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@ModelAttribute("curso") final Curso c, final BindingResult bindingResult) {
		ModelAndView result;
		final Collection<Nivel> niveles = new ArrayList<Nivel>();
		niveles.add(Nivel.PRINCIPIANTE);
		niveles.add(Nivel.INTERMEDIO);
		niveles.add(Nivel.AVANZADO);
		niveles.add(Nivel.PROFESIONAL);
		result = new ModelAndView("curso/create");
		result.addObject("requestURI", "curso/create.do");
		result.addObject("niveles", niveles);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("curso") final Curso c, final BindingResult bindingResult) {
		ModelAndView result;
		final Curso curso = new Curso();
		curso.setTitulo(c.getTitulo());
		curso.setFechaini(c.getFechaini());
		curso.setFechafin(c.getFechafin());
		curso.setDiaSemana(c.getDiaSemana());
		curso.setHora(c.getHora());
		curso.setNivel(c.getNivel());
		try {
			this.cursoService.save(curso);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:create.do");
			result.addObject("curso", curso);
			result.addObject("message", "curso.commit.error");
		}
		return result;
	}

}
