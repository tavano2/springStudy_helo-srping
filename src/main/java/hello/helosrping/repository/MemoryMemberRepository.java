package hello.helosrping.repository;

import hello.helosrping.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> stroe = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        stroe.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(stroe.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return stroe.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(stroe.values());
    }

    public void clearStore() {
        stroe.clear();
    }
}
