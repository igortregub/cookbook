const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
    entry: "./src/main/frontend/app/index.js",
    output: {
        path: path.join(__dirname, "/target/classes/static/"),
        filename: "index_bundle.js"
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                },
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./src/main/frontend/public/index.html"
        })
    ],
    devServer: {
        port: 9001,
        proxy: {
            '/api': 'http://localhost:8080',
        },
    },
};