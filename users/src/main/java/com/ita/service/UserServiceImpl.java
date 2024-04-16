package com.ita.service;

import com.ita.UserServiceGrpc;
import com.ita.UserServiceOuterClass;
import com.ita.model.User;
import com.ita.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import jakarta.inject.Inject;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

        @Inject
        public UserRepository userRepository;

        @Override
        public void createUser(UserServiceOuterClass.CreateUserRequest request,
                        StreamObserver<UserServiceOuterClass.CreateUserResponse> responseObserver) {
                User user = new User();
                user.setUsername(request.getUser().getUsername());
                user.setEmail(request.getUser().getEmail());
                user.setPassword(request.getUser().getPassword());
                userRepository.persist(user);

                UserServiceOuterClass.User grpcUser = UserServiceOuterClass.User.newBuilder()
                                .setId(user.id)
                                .setUsername(user.getUsername())
                                .setEmail(user.getEmail())
                                .setPassword(user.getPassword())
                                .build();

                UserServiceOuterClass.CreateUserResponse response = UserServiceOuterClass.CreateUserResponse
                                .newBuilder()
                                .setUser(grpcUser)
                                .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
        }

        @Override
        public void getUser(UserServiceOuterClass.GetUserRequest request,
                        StreamObserver<UserServiceOuterClass.GetUserResponse> responseObserver) {
                User user = userRepository.findById(request.getId());

                if (user != null) {
                        UserServiceOuterClass.User grpcUser = UserServiceOuterClass.User.newBuilder()
                                        .setId(user.id)
                                        .setUsername(user.getUsername())
                                        .setEmail(user.getEmail())
                                        .setPassword(user.getPassword())
                                        .build();

                        UserServiceOuterClass.GetUserResponse response = UserServiceOuterClass.GetUserResponse
                                        .newBuilder()
                                        .setUser(grpcUser)
                                        .build();

                        responseObserver.onNext(response);
                }

                responseObserver.onCompleted();
        }

        @Override
        public void updateUser(UserServiceOuterClass.UpdateUserRequest request,
                        StreamObserver<UserServiceOuterClass.UpdateUserResponse> responseObserver) {
                User user = userRepository.findById(request.getUser().getId());

                if (user != null) {
                        user.setUsername(request.getUser().getUsername());
                        user.setEmail(request.getUser().getEmail());
                        user.setPassword(request.getUser().getPassword());
                        userRepository.persist(user);

                        UserServiceOuterClass.User grpcUser = UserServiceOuterClass.User.newBuilder()
                                        .setId(user.id)
                                        .setUsername(user.getUsername())
                                        .setEmail(user.getEmail())
                                        .setPassword(user.getPassword())
                                        .build();

                        UserServiceOuterClass.UpdateUserResponse response = UserServiceOuterClass.UpdateUserResponse
                                        .newBuilder()
                                        .setUser(grpcUser)
                                        .build();

                        responseObserver.onNext(response);
                }

                responseObserver.onCompleted();
        }

        @Override
        public void deleteUser(UserServiceOuterClass.DeleteUserRequest request,
                        StreamObserver<UserServiceOuterClass.DeleteUserResponse> responseObserver) {
                boolean deleted = userRepository.deleteById(request.getId());

                UserServiceOuterClass.DeleteUserResponse response = UserServiceOuterClass.DeleteUserResponse
                                .newBuilder()
                                .setSuccess(deleted)
                                .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
        }

        public void createUser(User user) {
                userRepository.persist(user);
        }
}
