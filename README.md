## FTC Swerve Drive
A simple implementation of a swerve drive for FTC. In a swerve drive, wheels have an additional motor attached which rotates them in place, allowing for the robot to move in any direction without rotation, as well as execute zero radius turns. Supports both 4 wheel a 3 wheel Swerve drive, in the OpModes `SwerveOpMode` and ``ThreeWheelOpMode`` respectively.

The robot is controlled using the left stick for movement, and the right stick for in place rotation.

To use, the motors spinning the wheels must be named `front_right_wheel`, `front_left_wheel`, `back_right_wheel`, and `back_left_wheel` respectively. The motors controlling the azimuth of the wheels must be named `front_right_rot`, `front_left_rot`, `back_right_rot`, and `back_left_rot`. Be sure to update the `ROTATE_TICKS_PER_ROTATION` variable in the `SwerveModule` class to the number of ticks in a rotation of the specific motor you are using. 

