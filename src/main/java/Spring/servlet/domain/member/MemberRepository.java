package Spring.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// this repository for member and contains CRUD methods using memory
public class MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();

    private static long sequence = 0L;

    // singleton pattern of member repository
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store 자체를 보호하기 위해서 새로운 arraylist 할당
    }

    public void clearStore() {
        store.clear();
    }



}
