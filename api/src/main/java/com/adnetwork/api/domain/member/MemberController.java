package com.adnetwork.api.domain.member;

import com.adnetwork.api.common.CommonResponse;
import com.adnetwork.api.domain.member.dto.LoginUserDto;
import com.adnetwork.common.member.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

  @PostMapping
  public ResponseEntity<CommonResponse<Void>> createMember(Member member) {
    return ResponseEntity.ok(CommonResponse.success());
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommonResponse<Void>> updateMember(@PathVariable("id") long id,
      Member member) {
    return ResponseEntity.ok(CommonResponse.success());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CommonResponse<Void>> deleteMember(@PathVariable("id") long id) {
    return ResponseEntity.ok(CommonResponse.success());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommonResponse<CommonResponse<LoginUserDto>>> getMembers() {
    return null;
  }
}
