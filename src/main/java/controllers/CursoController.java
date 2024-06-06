
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ayuda.Ayuda;
import domain.Academia;
import domain.Alumno;
import domain.Curso;
import domain.Estado_Curso;
import domain.Estilo;
import domain.Nivel;
import domain.Solicitud;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.AcademiaService;
import services.AlumnoService;
import services.CursoService;
import services.EstiloService;
import services.SolicitudService;

@Controller
@RequestMapping("/curso")
public class CursoController extends AbstractController {

	@Autowired
	private CursoService		cursoService;
	@Autowired
	private AcademiaService		academiaService;
	@Autowired
	private AlumnoService		alumnoService;
	@Autowired
	private SolicitudService	solicitudService;
	@Autowired
	private EstiloService		estiloService;

	private int					cursoId	= -1;


	@RequestMapping(value = "/listForAnonymous", method = RequestMethod.GET)
	public ModelAndView listAnonymous(@ModelAttribute("ayuda") final Ayuda sol, final BindingResult bindingResult) {
		ModelAndView result;
		Collection<Curso> cursos;
		cursos = this.cursoService.findAll();
		result = new ModelAndView("curso/list");
		result.addObject("requestURI", "curso/list.do");
		result.addObject("cursos", cursos);
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@ModelAttribute("ayuda") final Ayuda sol, final BindingResult bindingResult) {
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
			final List<Estilo> estilos = (List<Estilo>) this.estiloService.findAll();
			result = new ModelAndView("curso/list");
			result.addObject("requestURI", "curso/list.do");
			result.addObject("cursos", cursos);
			result.addObject("estilos", estilos);
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

		final Collection<Estilo> estilos = this.estiloService.findAll();

		/*
		 * final Collection<String> estilos = new ArrayList<String>();
		 * for (int i = 0; i < estilosCompletos.size(); i++)
		 * estilos.add(estilosCompletos.get(i).getNombre());
		 */

		result = new ModelAndView("curso/create");
		result.addObject("requestURI", "curso/create.do");
		result.addObject("niveles", niveles);
		result.addObject("estilos", estilos);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("curso") final Curso c, final BindingResult bindingResult) {
		ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final List<Academia> academias = (List<Academia>) this.academiaService.findByUsername(user.getUsername());
		final Academia a = academias.get(0);
		final Curso curso = new Curso();
		curso.setTitulo(c.getTitulo());
		curso.setDiaSemana(c.getDiaSemana());
		curso.setHora(c.getHora());
		curso.setNivel(c.getNivel());
		curso.setFechaini(c.getFechaini());
		curso.setFechafin(c.getFechafin());
		curso.setAcademia(a);

		System.out.println("Estilo: " + c.getEstilo());
		curso.setEstilo(c.getEstilo());
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

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ModelAndView request(@RequestParam final int cursoId) {
		ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final List<Alumno> alumnos = (List<Alumno>) this.alumnoService.findByUsername(user.getUsername());
		final Alumno a = alumnos.get(0);
		final List<Curso> cs = (List<Curso>) this.cursoService.findById(cursoId);
		final Curso c = cs.get(0);
		final Solicitud s = new Solicitud();
		//final Estilo estilo = this.estiloService.findByName(name)
		s.setEstado(Estado_Curso.PENDIENTE);
		s.setMomento(new Date());
		s.setCurso(c);
		s.setAlumno(a);
		List<Solicitud> solicitudes = c.getSolicitudes();
		if (solicitudes == null)
			solicitudes = new ArrayList<Solicitud>();
		solicitudes.add(s);
		c.setSolicitudes(solicitudes);
		List<Solicitud> solicitudesa = a.getSolicitudes();
		if (solicitudesa == null)
			solicitudesa = new ArrayList<Solicitud>();
		solicitudesa.add(s);
		a.setSolicitudes(solicitudesa);
		try {
			while (this.solicitudService.countByAlumnoAndCurso(a, c) < 1)
				this.solicitudService.save(s);
			this.cursoService.save(c);
			this.alumnoService.save(a);
			result = new ModelAndView("redirect:/solicitud/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("curso", c);
			result.addObject("message", "curso.commit.error");
			result.addObject("alumno", a);
			result.addObject("message", "alumno.commit.error");
			result.addObject("solicitud", s);
			result.addObject("message", "solicitud.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/deleteCurso", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@RequestParam final int cursoId) {
		ModelAndView result;

		this.cursoService.delete(cursoId);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/editCurso", method = RequestMethod.GET)
	public ModelAndView createEdit(@RequestParam final int cursoId) {
		ModelAndView result;

		final List<Curso> cursos = (List<Curso>) this.cursoService.findById(cursoId);
		final Curso curso = cursos.get(0);

		this.cursoId = cursoId;

		result = new ModelAndView("curso/edit");
		result.addObject("requestURI", "curso/editCurso.do");
		result.addObject("curso", curso);

		return result;
	}

	@RequestMapping(value = "/editCurso", method = RequestMethod.POST)
	public ModelAndView saveEdit(@ModelAttribute("curso") final Curso c, final BindingResult bindingResult) {
		ModelAndView result;

		final List<Curso> cursos = (List<Curso>) this.cursoService.findById(this.cursoId);
		final Curso curso = cursos.get(0);

		curso.setTitulo(c.getTitulo());
		curso.setFechaini(c.getFechaini());
		curso.setFechafin(c.getFechafin());
		curso.setDiaSemana(c.getDiaSemana());
		curso.setHora(c.getHora());
		curso.setNivel(c.getNivel());

		//estilo.setCursos();

		this.cursoId = -1;

		try {

			this.cursoService.save(curso);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("curso", curso);
			result.addObject("message", "tutorial.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/giveEstilo", method = RequestMethod.POST)
	public ModelAndView giveEstilo(@ModelAttribute("ayuda") final Ayuda sol, @RequestParam final int cursoId, final BindingResult bindingResult) {
		ModelAndView result;

		final List<Curso> cursos = (List<Curso>) this.cursoService.findById(cursoId);
		final Curso curso = cursos.get(0);
		final List<Estilo> estilos = (List<Estilo>) this.estiloService.findByName(sol.getEstilo());
		final Estilo estilo = estilos.get(0);

		curso.setEstilo(estilo);

		try {
			this.cursoService.save(curso);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("curso", curso);
			result.addObject("message", "curso.commit.error");
		}

		return result;
	}
}
