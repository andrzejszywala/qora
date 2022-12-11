import { Component, ViewChild } from '@angular/core';
import { MenuItem, TreeNode } from 'primeng/api';
import { SqlResource } from './business/sql/boundary/sql.resource';
import { ExplainPlanRow } from './business/sql/model/explain-plan-row';
import { ZbiorDanych } from './zbiordanych';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {
	isCollapsed = false;
	defaultCheckedKeys = ['10020'];

	

	items: MenuItem[] = [
		{
			label: 'File',
			items: [{
				label: 'New',
				icon: 'pi pi-fw pi-plus',
				items: [
					{ label: 'Project' },
					{ label: 'Other' },
				]
			},
			{ label: 'Open' },
			{ label: 'Quit' }
			]
		},
		{
			label: 'Edit',
			icon: 'pi pi-fw pi-pencil',
			items: [
				{ label: 'Delete', icon: 'pi pi-fw pi-trash' },
				{ label: 'Refresh', icon: 'pi pi-fw pi-refresh' }
			]
		}
	];
	defaultSelectedKeys = ['10010'];
	defaultExpandedKeys = ['100', '1001'];
	sql: string = "";
	dataSet: ZbiorDanych[] = [];
	editorOptions = { theme: 'vs-light', language: 'sql' };
	contentHeight = 400;

	messages: string[] = [];
	id = -1;

	constructor(private sqlResource: SqlResource) {

	}


	onExecuteSql(): void {

		this.sqlResource.executeCommand(this.sql).subscribe(r => {
			this.dataSet = r.zbioryDanych
			this.messages = r.komunikaty;
		});
	}

	onExplainPlan(): void {
		this.sqlResource.explainPlan(this.sql).subscribe(r => {
			// this.buildExplainPlanTree(r);
		});
	}
	// buildExplainPlanTree(rows: ExplainPlanRow[]) {
	//   let nodes: NzTreeNodeOptions[] = [];
	//   rows.forEach(row => {
	//     nodes[row.id] = {
	//       title: row.operation,
	//       key: row.id + '',
	//       children: []
	//     };
	//   });

	//   rows.forEach(row => {
	//     if (row.parentId != null) {
	//       nodes[row.parentId].children?.push(nodes[row.id]);
	//     }
	//   });

	//   nodes.forEach(n => {
	//     n.isLeaf = n.children?.length == 0;
	//     n.expanded = true;
	//     n.selectable = false;
	//   });
	//   this.explainPlan = [];
	//   this.explainPlan.push(nodes[0]);
	// }

	// onContentResize({ height }: NzResizeEvent): void {
	//   cancelAnimationFrame(this.id);
	//   this.id = requestAnimationFrame(() => {
	//     this.contentHeight = height!;
	//   });
	// }
}
