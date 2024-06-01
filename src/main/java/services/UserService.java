
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserAccountRepository userRepository;


	public void save(final UserAccount user) {
		this.userRepository.save(user);
	}
}
