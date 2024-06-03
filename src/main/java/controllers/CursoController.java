
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Academia;
import domain.Curso;
import domain.Nivel;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.AcademiaService;
import services.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController extends AbstractController {

	@Autowired
	private CursoService	cursoService;
	@Autowired
	private AcademiaService	academiaService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curso> cursos;
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
			cursos = this.cursoService.findByAcademia(academia);
			result = new ModelAndView("curso/list");
			result.addObject("requestURI", "curso/list.do");
			result.addObject("cursos", cursos);
		} else {
			cursos = this.cursoService.findAll();
			result = new ModelAndView("curso/list");
			result.addObject("requestURI", "curso/list.do");
			result.addObject("cursos", cursos);
		}
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
