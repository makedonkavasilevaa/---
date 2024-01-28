package mk.ukim.finki.authservice.controller;

import mk.ukim.finki.authservice.dto.JwtAuthenticationDto;
import mk.ukim.finki.authservice.dto.RefreshTokenRequest;
import mk.ukim.finki.authservice.dto.SignInRequest;
import mk.ukim.finki.authservice.dto.SignUpRequest;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUp_shouldReturnUserAndHttpStatusOk() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        User mockedUser = new User(); // Mock your user object as needed
        when(authenticationService.signUp(signUpRequest)).thenReturn(mockedUser);

        // Act
        ResponseEntity<User> response = authController.signUp(signUpRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedUser, response.getBody());
    }

    @Test
    void signIn_shouldReturnJwtAuthenticationDtoAndHttpStatusOk() {
        // Arrange
        SignInRequest signInRequest = new SignInRequest();
        JwtAuthenticationDto mockedDto = new JwtAuthenticationDto(); // Mock your DTO object as needed
        when(authenticationService.login(signInRequest)).thenReturn(mockedDto);

        // Act
        ResponseEntity<JwtAuthenticationDto> response = authController.signIn(signInRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedDto, response.getBody());
    }

    @Test
    void refresh_shouldReturnJwtAuthenticationDtoAndHttpStatusOk() {
        // Arrange
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        JwtAuthenticationDto mockedDto = new JwtAuthenticationDto(); // Mock your DTO object as needed
        when(authenticationService.refreshToken(refreshTokenRequest)).thenReturn(mockedDto);

        // Act
        ResponseEntity<JwtAuthenticationDto> response = authController.refresh(refreshTokenRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedDto, response.getBody());
    }
}
