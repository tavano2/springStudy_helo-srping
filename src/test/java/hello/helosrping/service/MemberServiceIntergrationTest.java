package hello.helosrping.service;

import hello.helosrping.domain.Member;
import hello.helosrping.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {

    // 테스트시에는 바로 가져다 쓰고 자원을 버리기 때문에 직접적으로 필드에 의존시켜도 된다.
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    // 테스트는 반복 할 수 있어야 한다.
    // 만일 트랜잭셔널 어노테이션을 쓰지 않는다면 db에 commit 되기 때문에
    // 반복 테스트가 불가능하다.
    // 즉 테스트 케이스에 트랜잭셔널 어노테이션을 쓰면 db rollback을 기능을 지원해준다.
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");
        // when
        Long saveId = memberService.join(member);
        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);
        IllegalStateException e = Assertions
                .assertThrows(IllegalStateException.class
                        , () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}