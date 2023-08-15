package hello.helosrping.service;

import hello.helosrping.domain.Member;
import hello.helosrping.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    // 기존 서비스 인스턴스와 일치시키기 위해
    // 해당 기존 서비스 코드에 생성자를 만들어 초기화 시킨다.
    // 즉 내 코드에서 new하지 않고 외부에서 new하여 인스턴스를 생성시키는 기법이다.
    // 이 기법이 DI라고 한다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 테스트는 과감하게 한글로 메소드명을 지어도 된다.
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

    // 테스트는 정상 플로우보다
    // 예외 플로우가 중요하다.
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
        /*
         try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        // then
    }

    @Test
    void 전체조회() {
    }

    @Test
    void 하나조회() {
    }
}