
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ayuda.Ayuda;
import domain.Academia;
import domain.Alumno;
import domain.Estado_Curso;
import domain.Solicitud;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.AcademiaService;
import services.AlumnoService;
import services.SolicitudService;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController extends AbstractController {

	@Autowired
	private SolicitudService	solicitudService;
	@Autowired
	private AlumnoService		alumnoService;
	@Autowired
	private AcademiaService		academiaService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@ModelAttribute("ayuda") final Ayuda sol, final BindingResult bindingResult) {
		ModelAndView result;
		final Collection<Solicitud> solicitudes;
		final UserAccount user = LoginService.getPrincipal();
		final List<Authority> authorities = (List<Authority>) user.getAuthorities();
		final boolean b = false;
		if (authorities.get(0).getAuthority().equals("ACADEMIA")) {
			final List<Academia> academias = (List<Academia>) this.academiaService.findByUsername(user.getUsername());
			final Academia academia = academias.get(0);
			solicitudes = this.solicitudService.findByAcademia(academia);
			final Collection<Estado_Curso> estados = new ArrayList<Estado_Curso>();
			estados.add(Estado_Curso.PENDIENTE);
			estados.add(Estado_Curso.ACEPTADO);
			estados.add(Estado_Curso.RECHAZADO);
			result = new ModelAndView("solicitud/list");
			result.addObject("requestURI", "solicitud/list.do");
			result.addObject("solicitudes", solicitudes);
			result.addObject("estados", estados);
		} else if (authorities.get(0).getAuthority().equals("ALUMNO")) {
			final List<Alumno> al = (List<Alumno>) this.alumnoService.findByUsername(user.getUsername());
			solicitudes = this.solicitudService.findByAlumno(al.get(0));
			result = new ModelAndView("solicitud/list");
			result.addObject("requestURI", "solicitud/list.do");
			result.addObject("solicitudes", solicitudes);
		} else {
			solicitudes = this.solicitudService.findAll();
			result = new ModelAndView("solicitud/list");
			result.addObject("requestURI", "solicitud/list.do");
			result.addObject("solicitudes", solicitudes);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("ayuda") final Ayuda sol, @RequestParam final int solicitudId, final BindingResult bindingResult) {
		ModelAndView result;
		final List<Solicitud> solicitudes = (List<Solicitud>) this.solicitudService.findById(solicitudId);
		final Solicitud s = solicitudes.get(0);
		s.setEstado(sol.getEstado());
		try {
			this.solicitudService.save(s);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("solicitud", s);
			result.addObject("message", "solicitud.commit.error");
		}
		return result;
	}
}
