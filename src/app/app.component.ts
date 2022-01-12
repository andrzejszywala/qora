import { Component } from '@angular/core';
import {MenubarModule} from 'primeng/menubar';
import {MenuItem, TreeNode} from 'primeng/api';
import { NodeService } from './nodeservice';
import { ProductService } from './productservice';
import { Product } from './product';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'qora';
  items: MenuItem[];
  files: TreeNode[] = [];
  products: Product[] = [];

    constructor(
            private nodeService: NodeService,
            private productService: ProductService) {
        this.nodeService.getFiles().then(files => this.files = files);
        this.productService.getProductsSmall().then(data => this.products = data);
        
        this.items = [
            {
                label: 'File',
                items: [{
                        label: 'New', 
                        icon: 'pi pi-fw pi-plus',
                        items: [
                            {label: 'Project'},
                            {label: 'Other'},
                        ]
                    },
                    {label: 'Open'},
                    {label: 'Quit'}
                ]
            },
            {
                label: 'Edit',
                icon: 'pi pi-fw pi-pencil',
                items: [
                    {label: 'Delete', icon: 'pi pi-fw pi-trash'},
                    {label: 'Refresh', icon: 'pi pi-fw pi-refresh'}
                ]
            }
        ];
    }
}
