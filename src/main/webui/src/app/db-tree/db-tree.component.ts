import { Component } from "@angular/core";
import { TreeNode } from "primeng/api";
import { ProfilesResource } from "../business/oracle/profiles/boundary/profiles.resource";
import { RolesResource } from "../business/oracle/roles/boundary/roles.resource";
import { SessionsResource } from "../business/oracle/sessions/boundary/sessions.resource";
import { Session } from "../business/oracle/sessions/entity/session";
import { UsersResource } from "../business/oracle/users/boundary/users.resource";
import { TablesResource } from "../business/oracle/tables/boundary/tables.resource";
import { IndexesResource } from "../business/oracle/indexes/boundary/indexes.resource";
import { User } from "../business/oracle/users/entity/user";

@Component({
	selector: 'q-db-tree',
	templateUrl: './db-tree.component.html',
	styleUrls: ['./db-tree.component.scss']
})
export class DbTreeComponent {

	private sessionsNode: TreeNode = {
		label: "Sessions",
		expandedIcon: "pi pi-folder-open",
		collapsedIcon: "pi pi-folder",
		leaf: false
	};

	private schemasNode: TreeNode = {
		label: "Schemas",
		expandedIcon: "pi pi-folder-open",
		collapsedIcon: "pi pi-folder",
		leaf: false
	};

	private usersNode: TreeNode = {
		label: "Users",
		expandedIcon: "pi pi-folder-open",
		collapsedIcon: "pi pi-folder",
		leaf: false
	};

	private profilesNode: TreeNode = {
		label: "Profiles",
		expandedIcon: "pi pi-folder-open",
		collapsedIcon: "pi pi-folder",
		leaf: false
	};

	private rolesNode: TreeNode = {
		label: "Roles",
		expandedIcon: "pi pi-folder-open",
		collapsedIcon: "pi pi-folder",
		leaf: false
	};

	private securityNode: TreeNode = {
		label: "Security",
		expandedIcon: "pi pi-folder-open",
		collapsedIcon: "pi pi-folder",
		leaf: false,
		children: [this.usersNode, this.profilesNode, this.rolesNode]
	};

	files: TreeNode[] = [
		{
			label: "Instance",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			children: [{
				label: "Configuration"
			}, this.sessionsNode
			]
		},
		this.schemasNode,
		this.securityNode,
		{
			label: "Storage",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder"
		}
	];


	constructor(private sessionsResource: SessionsResource,
		private usersResource: UsersResource,
		private profilesResource: ProfilesResource,
		private rolesResource: RolesResource,
		private tablesResource: TablesResource,
		private indexesResource: IndexesResource) { }


	loadNode(event: any) {
		if (event.node == this.sessionsNode) {
			this.sessionsResource.sessions().subscribe(sessions => this.sessionsNode.children = this.createNodes(sessions));
		} else if (event.node == this.schemasNode) {
			this.usersResource.users().subscribe(users => this.schemasNode.children = users.map(u => this.createSchemaNode(u, event.node)));
		} else if (event.node == this.usersNode) {
			this.usersResource.users().subscribe(users => this.usersNode.children = users.map(s => {
				return {
					label: s.userName,
					data: s
				};
			}));
		} else if (event.node == this.profilesNode) {
			this.profilesResource.profiles().subscribe(profiles => this.profilesNode.children = profiles.map(p => {
				return {
					label: p.profile,
					data: p
				};
			}));
		} else if (event.node == this.rolesNode) {
			this.rolesResource.roles().subscribe(roles => this.rolesNode.children = roles.map(p => {
				return {
					label: p.role,
					data: p
				};
			}));
		} else if (event.node.label === 'Tables') {
			this.tablesResource.tables(event.node.data.userName).subscribe(tables => event.node.children = tables.map(t => {
				return {
					label: t.tableName,
					data: t
				};
			}));
		} else if (event.node.label === 'Indexes') {
			this.indexesResource.indexes(event.node.data.userName).subscribe(indexes => event.node.children = indexes.map(i => {
				return {
					label: i.indexName,
					data: i
				};
			}));
		}
	}

	createSchemaNode(user: User, parent: TreeNode): TreeNode<User> {
		let tablesNode: TreeNode = {
			label: "Tables",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let indexesNode: TreeNode = {
			label: "Indexes",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let viewsNode: TreeNode = {
			label: "Views",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let synonymsNode: TreeNode = {
			label: "Synonyms",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let sequencesNode: TreeNode = {
			label: "Sequences",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let clustersNode: TreeNode = {
			label: "Clusters",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let packagesNode: TreeNode = {
			label: "Packages",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let packagesBodyNode: TreeNode = {
			label: "Packages body",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let proceduresNode: TreeNode = {
			label: "Procedures",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let functionsNode: TreeNode = {
			label: "Functions",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		let triggersNode: TreeNode = {
			label: "Triggers",
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			leaf: false,
			parent: parent,
			data: user
		}

		return {
			label: user.userName,
			expandedIcon: "pi pi-folder-open",
			collapsedIcon: "pi pi-folder",
			children: [tablesNode, indexesNode, viewsNode, synonymsNode, sequencesNode, clustersNode,
				packagesNode, packagesBodyNode, proceduresNode, functionsNode, triggersNode]
		};
	}

	createNodes(nodes: any[]): TreeNode<any>[] {
		return nodes.map(s => {
			return {
				label: s.name,
				data: s
			};
		});
	}

	createSecurityNode() {

	}

}