<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sign Up page</title>
  <link rel="stylesheet" href="">
  <script src="./assets/tailwindcss.js"></script>
  <link rel="stylesheet" href="./assets/styles.css">
</head>

<body>
  <div class="container rounded-lg mx-auto">
    <form action="#" class=" w-[460px] mx-auto my-16">
      <h1 class="text-[2rem] text-center mb-4">Sign Up</h1>
      <p class="text-center">It's free and only takes a minute.</p>
      <div class="inputWrapper">
        <label for="username">Username</label> <br>
        <input type="text" name="username" id="username" class="input" required>
      </div>
      <div class="inputWrapper">
        <label for="email">Email Address</label> <br>
        <input type="email" name="email" id="email" class="input" required>
      </div>
      <div class="inputWrapper">
        <label for="pw">Password</label> <br>
        <input type="password" name="pw" id="pw" class="input" required>
      </div>
      <div  class="inputWrapper">
        <label for="ConPw">Confirm Password</label> <br>
        <input type="password" name="ConPw" id="ConPw" class="input border-2" required>
      </div>
      <button type="submit" class="bg-[#49C1A2] text-white w-full rounded-lg h-[52px] my-10 text-[1.2rem] font-bold">Sign Up</button>
      <p class="text-center">By clicking the Sign Up button, you agree to our <br> 
          <span class="text-[#49C1A2]"> Terms & Conditions</span>, and <span class="text-[#49C1A2]"> Privacy Policy</span>.</p>
    </form>
  </div>
</body>

</html>