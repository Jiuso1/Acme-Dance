
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ayuda.MD5;
import domain.Alumno;
import security.LoginService;
import security.UserAccount;
import services.AlumnoService;
import services.UserService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController extends AbstractController {

	@Autowired
	private AlumnoService	alumnoService;
	@Autowired
	private UserService		userService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final List<Alumno> alumnos = (List<Alumno>) this.alumnoService.findByUsername(user.getUsername());
		final Alumno alumno = alumnos.get(0);
		result = new ModelAndView("alumno/edit");
		result.addObject("requestURI", "alumno/edit.do");
		result.addObject("alumno", alumno);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editAlumno(@ModelAttribute("alumno") final Alumno a, final BindingResult bindingResult) {
		ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final List<Alumno> alumnos = (List<Alumno>) this.alumnoService.findByUsername(user.getUsername());
		final Alumno alumno = alumnos.get(0);
		alumno.setNombre(a.getNombre());
		alumno.setApellidos(a.getApellidos());
		alumno.setEmail(a.getEmail());
		alumno.setDireccion(a.getDireccion());
		alumno.setTelefono(a.getTelefono());
		alumno.setUsername(a.getUsername());
		alumno.setPassword(MD5.getMd5(a.getPassword()));
		user.setUsername(a.getUsername());
		user.setPassword(MD5.getMd5(a.getPassword()));
		try {

			this.alumnoService.save(alumno);
			this.userService.save(user);
			result = new ModelAndView("redirect:/security/logout.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
			result.addObject("alumno", alumno);
			result.addObject("message", "alumno.commit.error");
			result.addObject("user", user);
			result.addObject("message", "user.commit.error");
		}
		return result;
	}
}
