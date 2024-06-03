
package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Alumno;
import domain.Solicitud;
import security.LoginService;
import security.UserAccount;
import services.AlumnoService;
import services.SolicitudService;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController extends AbstractController {

	@Autowired
	private SolicitudService	solicitudService;
	@Autowired
	private AlumnoService		alumnoService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Solicitud> solicitudes;
		final UserAccount user = LoginService.getPrincipal();
		final List<Alumno> al = (List<Alumno>) this.alumnoService.findByUsername(user.getUsername());
		solicitudes = this.solicitudService.findByAlumno(al.get(0));
		result = new ModelAndView("solicitud/list");
		result.addObject("requestURI", "solicitud/list.do");
		result.addObject("solicitudes", solicitudes);
		return result;
	}
}
