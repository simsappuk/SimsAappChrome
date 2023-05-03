
package com.ims.orm.common;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Annotation for UserGroup.
 *
 * @author Intesar Mohammed
 */
//@Cacheable(value = "userCache" )
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="user_region")
@Entity
//@SolrDocument(solrCoreName = "collection1")
public class UserGroup extends TenantBaseEntity {

    private static final long serialVersionUID = -2175150694352541150L;

 //   @Indexed("usergroup_name_t")
    private String name;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "users_user_groups",
            joinColumns = {
                    @JoinColumn(name = "user_groups_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "users_id", nullable = false, updatable = false)}
    )
    private Set<Users> users = new HashSet<>();
    
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "user_group_users_admin",
            joinColumns = {
                    @JoinColumn(name = "user_group_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", nullable = false, updatable = false)}
    )
    private Set<Users> admins = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public Set<Users> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Users> admins) {
		this.admins = admins;
	}
/*
	@Override
    public String toString() {
        return this.getId();
    }
*/
}
