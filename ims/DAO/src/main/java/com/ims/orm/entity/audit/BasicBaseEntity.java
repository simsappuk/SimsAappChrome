
package com.ims.orm.entity.audit;

import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author Intesar Mohammed
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class BasicBaseEntity extends AbstractAuditable<String, String> {



}
