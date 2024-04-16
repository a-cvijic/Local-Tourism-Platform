package com.ita;

import com.ita.model.User;
import com.ita.repository.UserRepository;
import com.ita.service.UserServiceImpl;
import io.grpc.stub.StreamObserver;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Captor
    ArgumentCaptor<User> userCaptor;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl();
        userService.userRepository = userRepository;
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Act
        userService.createUser(user);

        // Assert
        verify(userRepository).persist(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals("testuser", capturedUser.getUsername());
        assertEquals("test@example.com", capturedUser.getEmail());
        assertEquals("password", capturedUser.getPassword());
    }

    @Test
    void testGetUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.id = userId;
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userRepository.findById(userId)).thenReturn(user);

        // Act
        UserServiceOuterClass.GetUserRequest request = UserServiceOuterClass.GetUserRequest.newBuilder()
                .setId(userId)
                .build();
        StreamObserver<UserServiceOuterClass.GetUserResponse> responseObserver = mock(StreamObserver.class);
        userService.getUser(request, responseObserver);

        // Assert
        verify(userRepository).findById(userId);
        verify(responseObserver).onNext(any(UserServiceOuterClass.GetUserResponse.class));
        verify(responseObserver).onCompleted();
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.id = userId;
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userRepository.findById(userId)).thenReturn(user);

        // Act
        UserServiceOuterClass.User grpcUser = UserServiceOuterClass.User.newBuilder()
                .setId(userId)
                .setUsername("updateduser")
                .setEmail("updated@example.com")
                .setPassword("updatedpassword")
                .build();
        UserServiceOuterClass.UpdateUserRequest request = UserServiceOuterClass.UpdateUserRequest.newBuilder()
                .setUser(grpcUser)
                .build();
        StreamObserver<UserServiceOuterClass.UpdateUserResponse> responseObserver = mock(StreamObserver.class);
        userService.updateUser(request, responseObserver);

        // Assert
        verify(userRepository).findById(userId);
        verify(userRepository).persist(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals("updateduser", capturedUser.getUsername());
        assertEquals("updated@example.com", capturedUser.getEmail());
        assertEquals("updatedpassword", capturedUser.getPassword());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        when(userRepository.deleteById(userId)).thenReturn(true);

        // Act
        UserServiceOuterClass.DeleteUserRequest request = UserServiceOuterClass.DeleteUserRequest.newBuilder()
                .setId(userId)
                .build();
        StreamObserver<UserServiceOuterClass.DeleteUserResponse> responseObserver = mock(StreamObserver.class);
        userService.deleteUser(request, responseObserver);

        // Assert
        verify(userRepository).deleteById(userId);
        verify(responseObserver).onNext(any(UserServiceOuterClass.DeleteUserResponse.class));
        verify(responseObserver).onCompleted();
    }
}
