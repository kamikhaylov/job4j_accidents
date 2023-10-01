package ru.job4j.accidents.repository.data;

public class Queries {

    protected static final String FIND_ACCIDENT_ALL =
            "select distinct accident "
            + "from Accident accident "
            + "join fetch accident.rules "
            + "order by accident_id asc";

    protected static final String FIND_ACCIDENT_TYPE_ALL =
            "from AccidentType order by id asc";

    protected static final String FIND_RULE_ALL =
            "from Rule order by id asc";

    protected static final String FIND_RULES_BY_IDS =
            "from Rule where id in :ids";

}
