package com.gl.parcauto.aop;

import com.gl.parcauto.auth.IAuthenticationFacade;
import com.gl.parcauto.dto.ROLES;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.exception.AppException;
import com.gl.parcauto.exception.ResourceNotFoundException;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class DriverAuthorizationAspect {
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final IAuthenticationFacade authenticationFacade;

    @Pointcut("execution(* com.gl.parcauto.controller.DriverController.updateDriver(..))")
    public void updateDriver() {}
    @Pointcut("execution(* com.gl.parcauto.controller.DriverController.getDriverById(..))")
    public void getDriverById() {}
    @Pointcut("execution(* com.gl.parcauto.controller.DriverController.getTripsOfDriver(..))")
    public void getTripsOfDriver() {}
    @Pointcut("execution(* com.gl.parcauto.controller.DriverLicenseController.getLicenseById(..))")
    public void getLicenseById() {}
    @Pointcut("execution(* com.gl.parcauto.controller.DriverLicenseController.getLicensesForDriver(..))")
    public void getLicensesForDriver() {}
    @Pointcut("execution(* com.gl.parcauto.controller.VacationController.getVacationById(..))")
    public void getVacationById() {}
    @Pointcut("execution(* com.gl.parcauto.controller.VacationController.getVacationsForDriver(..))")
    public void getVacationsForDriver() {}

    @Before(value = "(updateDriver() || getDriverById() || getTripsOfDriver() || " +
            "getLicenseById() || getLicensesForDriver() || getVacationById() || " +
            "getVacationsForDriver())  && args(cin)")
    public void checkDriverAuthorization(String cin) {
        Authentication authentication = authenticationFacade.getAuthentication();
        if(authentication == null || !checkAuthorization(cin, authentication)) {
            throw new AppException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }
    private boolean checkAuthorization(String cin, Authentication authentication) {
        String username = authentication.getName();
        if(username == null) {
            return false;
        }

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "username", username)
        );

        String roleName = user.getRole().getName();

        if(roleName.equals(ROLES.ROLE_ADMIN.name()))
            return true;

        Driver driver = driverRepository.findById(cin).orElseThrow(() ->
                new ResourceNotFoundException("Driver", "id", cin)
        );

        return driver.getUser().getUsername().equals(username);
    }
}
