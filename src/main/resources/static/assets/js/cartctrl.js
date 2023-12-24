let app = angular.module("myapp", []);
app.controller("cartController", function ($scope, $http) {
    $scope.cart = {
        items: [],
        add(id) {
            let item = this.items.find(item => item.id === id)
            if (item) {
                item.qty++
                this.saveToLoCalStorage();

            } else {
                $http.get("/rest/api/product/" + id)
                    .then(resp => {
                        resp.data.qty = 1
                        this.items.push(resp.data)
                        this.saveToLoCalStorage()
                        console.log(resp.data)
                    })
                    .catch(err => {
                        console.log("error", err)
                    })
            }
            alert("Thêm Sản Phẩm Thành Công")
        },
        saveToLoCalStorage() {
            let json = JSON.stringify(angular.copy(this.items))
            localStorage.setItem("cart", json)
        },
        getCount() {
            return this.items.map(item => item.qty)
                .reduce((total, qty) => total + qty, 0)
        },
        getAmount() {
            return this.items.map(item => item.qty * item.price)
                .reduce((total, qty) => total + qty, 0)
        },
        loadFromLocalStorage() {
            let json = localStorage.getItem("cart")
            if (json != null) {
                this.items = JSON.parse(json)
            }
        },
        remove(id) {
            let index = this.items.findIndex(item => item.id === id)
            this.items.splice(index, 1)
            this.saveToLoCalStorage()
        },
        clear() {
            this.items = []
            this.saveToLoCalStorage();
        }

    }
    $scope.cart.loadFromLocalStorage()

    $scope.pay = function (){
        if(this.cart.items.length > 0 ){
            location.href = '/cart/order'
        }else {
            alert('Oops ,Không có sản phẩm nào rồi, mua ngay đi !')
            location.href = '/store'
        }
    }
    $scope.count = 0;

    $scope.Order = {
        status: 'Vừa Đặt Hàng',
        createDate: new Date(),
        createUpdate: new Date(),
        address: '',
        phoneNumbers: '',
        description: '',
        account: {
            email: $('#email-order').val()
        },

        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {
                        id: item.id
                    },
                    price: item.price,
                    quanity: item.qty
                }
            })
        },
        purchase() {
            $scope.count++
            if($scope.count > 1){
                let order = angular.copy(this)
                $http.post("/rest/api/cart/pay", order)
                    .then(resp => {
                        alert('Đặt Hàng Thành Công, Check Mail xem thông tin đơn hàng')
                        console.log("Success", resp.data)
                        location.href = "/thanks"
                        $scope.count =0
                    })
                    .catch(error => {
                        alert("Sorry , Lỗi Rồi!")
                        console.log("Errors", error)
                    })

            }else{
                alert("Đang xử lý")
            }

        }

    }
})
