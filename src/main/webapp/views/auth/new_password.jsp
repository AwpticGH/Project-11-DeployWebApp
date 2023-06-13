@extends('static.user')
@section('css-file', 'login.css')
@section('js-file', 'login.js')

@section('content')
    <div class="form-body">
        <div class="container">
            <div class="title">
                New Password
            </div>
            <div class="content">
                <form method="POST" action="{{ route('password.update') }}">
                    @csrf
                    <input type="hidden" name="token" value="{{ $request->route('token') }}">
                    <input type="hidden" name="email" value="{{ $request->email }}">
                    <div class="user-details">
                        <div class="input-box">
                            <label for="password" class="details">Password</label>
                            <input type="password" placeholder="Enter your password" id="password" name="password" required>
                        </div>
                        <div class="input-box">
                            <label for="password_confirmation" class="details">Password Confirmation</label>
                            <input type="password" placeholder="Confirm Your Password" id="password_confirmation" name="password_confirmation" required>
                        </div>
                    </div>
                    <div class="button">
                        <button class="button-login" type="Submit">Reset Password</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
@endsection
