@extends('static.user')
@section('css-file', 'login.css')
@section('js-file', 'login.js')

@section('content')
    <div class="form-body">
        <div class="container">
            <div class="title">
                Forgot Password
            </div>
            <div class="content">
                <form method="POST" action="{{ route('password.email') }}">
                    @csrf
                    <div class="user-details">
                        <div class="input-box">
                            <label for="email" class="details">Email</label>
                            <input type="email" placeholder="Enter your email" id="email" name="email" required>
                        </div>
                    </div>
                    <div class="button">
                        <button class="button-login" type="Submit">Send Link To Email</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
@endsection
