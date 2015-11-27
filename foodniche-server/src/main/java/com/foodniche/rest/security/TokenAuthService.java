package com.foodniche.rest.security;

import com.foodniche.db.dao.UserDao;
import com.foodniche.db.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alexey Dubrov
 *
 * Service for token based authentication.
 */

@Service
public class TokenAuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Authenticate user and generate token.
     * @param username user name
     * @param password password
     * @param rememberMe remember me option
     * @return token string
     * @throws AuthenticationException
     */
    public String authenticateByCredentials(String username, String password, boolean rememberMe) throws AuthenticationException{
        Users user = userDao.findUserByName(username);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return tokenRepository.createToken(user, 2);
            } else {
                throw new BadCredentialsException("Wrong password for user: " + username);
            }
        } else {
            throw new UsernameNotFoundException("No such user with username: " + username);
        }
    }

    /**
     * Authenticate in filter.
     * @param token auth token
     * @return rest auth token
     */
    public RestAuthenticationToken authenticate(String token) {
        RestAuthenticationToken authToken = null;
        Users user = tokenRepository.getUserByToken(token);

        if (user != null) {
            List<UserRole> roles = new ArrayList<>();

            authToken = new RestAuthenticationToken(user, roles);
        }

        return authToken;
    }
}
