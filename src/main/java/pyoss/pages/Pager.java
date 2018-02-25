package pyoss.pages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.util.List;

public class Pager {

    public static <T> Page<T> createPageFor(PageRequest request, List<T> list) {
        return PageableExecutionUtils.getPage(list, request, list::size);
    }

    public static PageRequest onePageRequest(int offset) {
        return new PageRequest(offset, 1);
    }
}
