var admin = require("firebase-admin")

var serviceAccount = require("./server_key.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
})

var token = "eAGp37FcRTKoftsNkKyL1e:APA91bGgdrR7AUeQ6zEFTNz5W7cDArPOVe8pd4-R4JreCGTRvE1IGuDCMSAFFYyKIE2PrVqAUqHAiTliqY0Lwe62SUmBkgFU5CAyN-daR1ugOd8HBP_D-QkR4YOmc7EJGUxOhhpS6wGU"

var fcm_message = {
    notification: {
        title:'noti title',
        body: 'noti body..'
    },
    data: {
        title: 'data title',
        value: '20'
    },
    token: token
}

admin.messaging().send(fcm_message)
    .then(function(response) {
        console.log('send ok...')
    })
    .catch(function(error) {
        console.log('send error...')
    })