package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Chronology;
import com.tinqin.cms.entities.ChronologyFilter;
import com.tinqin.cms.entities.IndexVM;
import com.tinqin.cms.entities.MessageLog;
import com.tinqin.cms.enums.Type;
import com.tinqin.cms.repositories.ChronologyRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Getter
public class ChronologyService {
    private final ChronologyRepository chronologyRepository;

    public void saveChronology(Chronology chronology) {
        chronologyRepository.save(chronology);
    }

    public IndexVM<MessageLog> getChronology(ChronologyFilter filter) {
        Page<Chronology> page = chronologyRepository.findChronologyByFilter(filter.email(), filter.createdAfter(), filter.createdBefore(),
                filter.type() == null ? "" : filter.type().getLabel(), PageRequest.of(filter.page(), filter.pageSize()));

        return new IndexVM<>(page.map(MessageLog::new));
    }

    public Set<String> getTypes() {
        return Arrays.stream(Type.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }
}
