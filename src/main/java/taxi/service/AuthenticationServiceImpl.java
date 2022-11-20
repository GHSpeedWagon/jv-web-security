package taxi.service;

import java.util.Optional;
import taxi.exception.AuthenticationException;
import taxi.lib.Injector;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final DriverService driverService
            = (DriverService) injector.getInstance(DriverService.class);

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isEmpty()) {
            throw new AuthenticationException("login or password is incorrect");
        }
        if (driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("login or password is incorrect");
    }
}
