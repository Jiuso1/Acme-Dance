
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ayuda.Ayuda;
import ayuda.MD5;
import domain.Academia;
import domain.Alumno;
import security.Authority;
import security.UserAccount;
import services.AcademiaService;
import services.AlumnoService;
import services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController {

	@Autowired
	private AcademiaService	academiaService;
	@Autowired
	private AlumnoService	alumnoService;
	@Autowired
	private UserService		userService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("register") final Ayuda d, final BindingResult bindingResult) {
		Assert.notNull(bindingResult);
		ModelAndView result;
		result = new ModelAndView("register/register");
		result.addObject("requestURI", "register/register.do");
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register2(@ModelAttribute("register") final Ayuda d, @ModelAttribute("academia") final Academia a, @ModelAttribute("alumno") final Alumno al, final BindingResult bindingResult) {
		Assert.notNull(bindingResult);
		Assert.notNull(d);
		ModelAndView result;
		if (d.getA() == 2) {
			result = new ModelAndView("register/registeracademy");
			result.addObject("requestURI", "register/registeracademy.do");
		} else {
			result = new ModelAndView("register/registeralumn");
			result.addObject("requestURI", "register/registeralumn.do");
		}
		return result;
	}

	@RequestMapping(value = "/registeracademy", method = RequestMethod.POST)
	public ModelAndView registerAcademy(@ModelAttribute("academia") final Academia a, final BindingResult bindingResult) {
		Assert.notNull(bindingResult);
		Assert.notNull(a);
		ModelAndView result;
		final Academia academia = new Academia();
		academia.setNombre(a.getNombre());
		academia.setApellidos(a.getApellidos());
		academia.setEmail(a.getEmail());
		academia.setDireccion(a.getDireccion());
		academia.setTelefono(a.getTelefono());
		academia.setUsername(a.getUsername());
		academia.setPassword(MD5.getMd5(a.getPassword()));
		academia.setRol(2);
		academia.setNombreComercial(a.getNombreComercial());
		final UserAccount usuario = new UserAccount();
		usuario.setUsername(a.getUsername());
		usuario.setPassword(MD5.getMd5(a.getPassword()));
		final Authority aut = new Authority();
		aut.setAuthority(Authority.ACADEMIA);
		usuario.addAuthority(aut);
		try {
			this.academiaService.save(academia);
			this.userService.save(usuario);
			result = new ModelAndView("redirect:/security/login.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:register.do");
			result.addObject("academia", academia);
			result.addObject("message", "academia.commit.error");
			result.addObject("usuario", usuario);
			result.addObject("message", "usuario.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/registeralumn", method = RequestMethod.POST)
	public ModelAndView registerAlumn(@ModelAttribute("alumno") final Alumno al, final BindingResult bindingResult) {
		Assert.notNull(bindingResult);
		Assert.notNull(al);
		ModelAndView result;
		final Alumno alumno = new Alumno();
		alumno.setNombre(al.getNombre());
		alumno.setApellidos(al.getApellidos());
		alumno.setEmail(al.getEmail());
		alumno.setDireccion(al.getDireccion());
		alumno.setTelefono(al.getTelefono());
		alumno.setUsername(al.getUsername());
		alumno.setPassword(MD5.getMd5(al.getPassword()));
		alumno.setRol(3);
		final UserAccount usuario = new UserAccount();
		usuario.setUsername(al.getUsername());
		usuario.setPassword(MD5.getMd5(al.getPassword()));
		final Authority aut = new Authority();
		aut.setAuthority(Authority.ALUMNO);
		usuario.addAuthority(aut);
		try {
			this.alumnoService.save(alumno);
			this.userService.save(usuario);
			result = new ModelAndView("redirect:/security/login.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:register.do");
			result.addObject("alumno", alumno);
			result.addObject("message", "alumno.commit.error");
			result.addObject("usuario", usuario);
			result.addObject("message", "usuario.commit.error");
		}
		return result;
	}
}
