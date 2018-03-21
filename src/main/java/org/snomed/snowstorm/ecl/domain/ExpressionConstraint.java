package org.snomed.snowstorm.ecl.domain;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.snomed.snowstorm.core.data.services.QueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class ExpressionConstraint implements Refinement {

	// Used to force no match
	static final String MISSING = "missing";
	static final Long MISSING_LONG = 111L;

	public Optional<Page<Long>> select(String path, QueryBuilder branchCriteria, boolean stated, Collection<Long> conceptIdFilter, PageRequest pageRequest, QueryService queryService) {
		if (isWildcard()) {
			return Optional.empty();
		}
		BoolQueryBuilder query = ConceptSelectorHelper.getBranchAndStatedQuery(branchCriteria, stated);
		addCriteria(query, path, branchCriteria, stated, queryService);
		return Optional.of(ConceptSelectorHelper.fetchIds(query, conceptIdFilter, pageRequest, queryService));
	}

	protected abstract boolean isWildcard();

}