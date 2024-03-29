package hello.helosrping;

import hello.helosrping.aop.TimeTraceAop;
import hello.helosrping.repository.MemberRepository;
import hello.helosrping.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    private final MemberRepository  memberRepository;


    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }



    /*
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    }
    */

}
