package com.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Pet;
import com.example.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

//本controller的功能描述
@Api(value = "pet", description = "the pet API")
public interface RemoteApI {

  //option的value的内容是这个method的描述，notes是详细描述，response是最终返回的json model。其他可以忽略
  @ApiOperation(value = "Add a new pet to the store", notes = "", response = Void.class, authorizations = {
      @Authorization(value = "petstore_auth", scopes = {
          @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
          @AuthorizationScope(scope = "read:pets", description = "read your pets")
          })
  }, tags={ "pet", })

  //这里是显示你可能返回的http状态，以及原因。比如404 not found, 303 see other
  @ApiResponses(value = { 
      @ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
  @RequestMapping(value = "/pet",
      produces = { "application/xml", "application/json" }, 
      consumes = { "application/json", "application/xml" },
      method = RequestMethod.POST)
  ResponseEntity<Void> addPet(
  //这里是针对每个参数的描述
  @ApiParam(value = "Pet object that needs to be added to the store" ,required=true ) @RequestBody Pet body);
  
  @ApiOperation(value = "createUser")  
  @ApiResponse(code = 400, message = "Invalid user supplied")
  public ResponseEntity<User> createUser(@RequestBody @ApiParam(value = "Created user object", required = true)  User user);
}
